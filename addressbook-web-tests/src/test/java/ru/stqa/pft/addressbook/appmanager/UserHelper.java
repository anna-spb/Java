package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.UserData;


import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.ArrayList;
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

    public void selectUser(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void submitNewUser() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void addNewUser() {
        click(By.linkText("add new"));
    }

    public void initUserModification() {
        click(By.xpath("//img[@alt='Edit']"));
    }

    public void submitModificationUser() {
        click(By.name("update"));
    }

    public void createUser(UserData userData) {
        addNewUser();
        fillUserForm(userData, true);
        submitNewUser();
        app.getNavigationHelper().goToHomePage();
    }

    public boolean isThereAnyUser() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getUserCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<UserData> getUserList() {
        List<UserData> users = new ArrayList<>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements){
            String firstName = element.getText();
            String lastName = element.getText();
            UserData user = new UserData(firstName, null, lastName,null, null, null,
            null, null);
            users.add(user);
        }

        return users;
    }
}
