package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.UserData;


import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

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

    public void selectUser() {
        click(By.name("selected[]"));
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
}
