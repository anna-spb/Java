package ru.stqa.pft.addressbook.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class addUserToGroup extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().isEmpty()) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test9"));
        }

        if (app.db().users().isEmpty()) {
            app.goTo().goToHomePage();
            app.user().create(new UserData().withFirstName("Neta").withLastName("Dedova").withCompany("Google")
                    .withAddress("Tel-Aviv").withHomePhone("123456789"));
        }

    }

    @Test
    public void testAddUserToGroup() {
        Users before = app.db().users();
        UserData chosenUser = before.iterator().next();
        GroupData chosenGroup;
        Groups groups = app.db().groups();
        if (groups.size() == chosenUser.getGroups().size()) {
            GroupData newGroup = new GroupData().withName("test_new");
            chosenGroup = newGroup;
            app.goTo().groupPage();
            app.group().create(newGroup);

        } else {
            chosenGroup = groups.iterator().next();
        }

        app.goTo().goToHomePage();
        app.user().initAddingToGroup(chosenUser, chosenGroup);
        app.goTo().goToHomePage();
        app.user().checkUserInGroup(chosenGroup);
        Users after = app.db().users();

        assertThat(after, equalTo(before));

        verifyUserListUI();
    }

}

