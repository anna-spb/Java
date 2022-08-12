package ru.stqa.pft.addressbook.test;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class UserDeletionFromGroupTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().isEmpty()) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test_new"));
        }

        if (app.db().users().isEmpty()) {
            app.goTo().goToHomePage();
            app.user().create(new UserData().withFirstName("Neta").withLastName("New").withCompany("Google")
                    .withAddress("Tel-Aviv").withHomePhone("123456789"));
        }
    }


    @Test
    public void testDeletionUserFromGroup() {
        Users before = app.db().users();
        UserData chosenUser = before.iterator().next();
        Groups groups = app.db().groups();
        GroupData chosenGroup = groups.iterator().next();

            app.goTo().goToHomePage();
            app.user().selectGroup(chosenGroup);
            if (app.user().count() == 0 ||
                    !app.user().isElementPresent(By.cssSelector("input[value = '" + chosenUser.getId() + "']"))) {
                app.user().checkAllPage();
                app.user().selectUserById(chosenUser.getId());
                app.user().addToGroup(chosenGroup);
                app.goTo().goToHomePage();
                app.user().selectGroup(chosenGroup);
            }

            app.user().deleteUserFromGroup(chosenUser);
            app.goTo().goToHomePage();
            app.user().checkUserInGroup(chosenGroup);
            Users after = app.db().users();

            assertThat(after, equalTo(before));

            verifyUserListUI();
        }
   }

