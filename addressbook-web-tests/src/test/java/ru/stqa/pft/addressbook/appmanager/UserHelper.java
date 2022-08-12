package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;

import java.util.List;

import static ru.stqa.pft.addressbook.test.TestBase.app;

public class UserHelper extends HelperBase {

    public UserHelper(WebDriver wd) {
        super(wd);
    }

    public void fillUserForm(UserData userData, boolean creation) {
        type(By.name("firstname"), userData.getFirstName());
        type(By.name("middlename"), userData.getMiddleName());
        type(By.name("lastname"), userData.getLastName());
        type(By.name("nickname"), userData.getNickname());
        type(By.name("company"), userData.getCompany());
        type(By.name("address"), userData.getAddress());
        type(By.name("home"), userData.getHomePhone());
        attach(By.name("photo"), userData.getPhoto());

        if (creation) {
            if (userData.getGroups().size() > 0) {
                Assert.assertTrue(userData.getGroups().size() == 1);
                new Select(wd.findElement(By.name("new_group")))
                        .selectByVisibleText(userData.getGroups().iterator().next().getName());
            } else {
                new Select(wd.findElement(By.name("new_group"))).getFirstSelectedOption();
            }
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }


    public void closeAlert() {
        wd.switchTo().alert().accept();
    }

    public void deleteSelectedUser() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void selectUserById(int id) {
        wd.findElement(By.cssSelector("input[value = '" + id + "']")).click();
    }

    public void addNewUser() {
        click(By.linkText("add new"));
    }

    public void submitNewUser() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void initUserModification(int id) {
        wd.findElement(By.xpath(String.format("//input[@value='%s']/../../td[8]/a", id))).click();
    }

    public void submitModificationUser() {
        click(By.name("update"));
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public void create(UserData user) {
        addNewUser();
        fillUserForm(user, true);
        submitNewUser();
        userCache = null;
        app.goTo().goToHomePage();
    }

    public void modify(UserData user) {
        selectUserById(user.getId());
        initUserModification(user.getId());
        fillUserForm(user, false);
        submitModificationUser();
        userCache = null;
        app.goTo().goToHomePage();
    }

    public void delete(UserData user) {
        selectUserById(user.getId());
        deleteSelectedUser();
        closeAlert();
        userCache = null;
        app.goTo().goToHomePage();
    }

    public void initAddingToGroup(UserData user, GroupData group) {
        selectUserById(user.getId());
        if (user.getGroups().contains(group)) {
            for (GroupData groupCopy : app.db().groups()) {
                if (!user.getGroups().contains(groupCopy)) {
                    addToGroup(groupCopy);
                    return;
                }
            }
        } else {
            addToGroup(group);
            return;
        }
    }

    public void addToGroup(GroupData group) {
        click(By.name("to_group"));
        new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(group.getName());
        click(By.name("add"));
    }

    public void checkUserInGroup(GroupData group) {
        click(By.name("group"));
        new Select(wd.findElement(By.name("group"))).selectByVisibleText(group.getName());
    }

    public void checkAllPage() {
        click(By.name("group"));
        new Select(wd.findElement(By.name("group"))).selectByVisibleText("[all]");
        wd.get("http://localhost/addressbook/?group=");
    }


    public void selectGroup(GroupData group) {
        click(By.name("group"));
        new Select(wd.findElement(By.name("group"))).selectByVisibleText(group.getName());
    }

    public void deleteUserFromGroup(UserData user) {
        selectUserById(user.getId());
        wd.findElement(By.name("remove")).click();
    }

    public void checkRightUser(int id) {
        if (isElementPresent(By.cssSelector("input[value = '" + id + "']"))) {
        }


    }

    private Users userCache = null;

    public Users all() {
        if (userCache != null) {
            return new Users(userCache);
        }
        userCache = new Users();
        List<WebElement> elements = wd.findElements(By.xpath("//*[@id=\"maintable\"]/tbody/tr[position() >= 2]"));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.xpath(".//td[1]")).
                    findElement(By.tagName("input")).getAttribute("value"));
            String lastName = element.findElement(By.xpath(".//td[2]")).getText();
            String firstName = element.findElement(By.xpath(".//td[3]")).getText();
            String address = element.findElement(By.xpath(".//td[4]")).getText();
            String allEmail = element.findElement(By.xpath(".//td[5]")).getText();
            String allPhones = element.findElement(By.xpath(".//td[6]")).getText();
            UserData user = new UserData().withId(id).withFirstName(firstName).withLastName(lastName)
                    .withAddress(address).withAllEmail(allEmail).withAllPhones(allPhones);
            userCache.add(user);
        }
        return new Users(userCache);
    }


    public UserData InfoFromEdit(UserData user) {
        initUserModification(user.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String phone2 = wd.findElement(By.name("phone2")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();
        return new UserData().withId(user.getId()).withFirstName(firstname).withLastName(lastname)
                .withAddress(address)
                .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).withPhone2(phone2)
                .withEmail(email).withEmail2(email2).withEmail3(email3);


    }
}

