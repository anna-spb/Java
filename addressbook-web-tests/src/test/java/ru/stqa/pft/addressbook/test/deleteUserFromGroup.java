package ru.stqa.pft.addressbook.test;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class deleteUserFromGroup extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().isEmpty()) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test5"));
        }

        if (app.db().users().isEmpty()) {
            app.goTo().goToHomePage();
            app.user().create(new UserData().withFirstName("Neta").withLastName("Dedova").withCompany("Google")
                    .withAddress("Tel-Aviv").withHomePhone("123456789"));
        }
    }


    @Test
    public void testUntitledTestCase() {
        Users before = app.db().users();
        app.goTo().goToHomePage();
        UserData chosenUser = before.iterator().next();
        GroupData group = new GroupData().withName("test 1");
        app.user().selectGroup(group);
        UserData user = new UserData().withId(chosenUser.getId());
        if (app.user().count() == 0 ||
                !app.user().isElementPresent(By.cssSelector("input[value = '" + user.getId() + "']"))) {
            app.user().checkAllPage();
            app.user().selectUserById(user.getId());
            app.user().addToGroup(group);
            app.goTo().goToHomePage();
            app.user().selectGroup(group);
        }

        app.user().deleteUserFromGroup(user);
        app.goTo().goToHomePage();
        app.user().checkUserInGroup(group);
        Users after = app.db().users();

        assertThat(after, equalTo(before));

        verifyUserListUI();
    }

}

