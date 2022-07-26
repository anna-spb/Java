package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        type(By.name("home"), userData.getPhone());
        if (creation) {
            if (isElementPresent(By.name(userData.getGroup()))) {
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(userData.getGroup());
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

    private void selectUserById(int id) {
        wd.findElement(By.cssSelector("input[value = '"+ id + "']")).click();
    }
    public void addNewUser() {
        click(By.linkText("add new"));
    }
    public void submitNewUser() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }


    public void initUserModification() {
      wd.findElement(By.xpath("//img[@alt='Edit']")).click();
    }
    public void submitModificationUser() {
        click(By.name("update"));
    }

    public void create(UserData userData) {
        addNewUser();
        fillUserForm(userData, true);
        submitNewUser();
        userCache = null;
        app.goTo().goToHomePage();
    }

    public void modify( UserData user) {
        selectUserById(user.getId());
        initUserModification();
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
    private Users userCache = null;

    public Users all() {
        if (userCache != null) {
            return new Users(userCache);
        }
        userCache = new Users();
        List<WebElement> elements = wd.findElements(By.xpath("//*[@id=\"maintable\"]/tbody/tr[position() >= 2]"));
        for (WebElement element : elements) {
            String lastName = element.findElement(By.xpath(".//td[2]")).getText();
            String firstName = element.findElement(By.xpath(".//td[3]")).getText();
            int id = Integer.parseInt(element.findElement(By.xpath(".//td[1]")).
                    findElement(By.tagName("input")).getAttribute("value"));
            UserData user = new UserData().withId(id).withFirstName(firstName).withLastName(lastName);
            userCache.add(user);
        }
        return new Users(userCache);
    }


}

