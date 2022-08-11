package ru.stqa.pft.addressbook.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class addUserToGroup extends TestBase {

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
    public void testAddUserToGroup() {
        Users before = app.db().users();
        app.goTo().goToHomePage();
        UserData chosenUser = before.iterator().next();
        UserData user = new UserData().withId(chosenUser.getId()).withFirstName("Neta");
        GroupData group = new GroupData().withName("test5");
        app.user().initAddingToGroup(user, group);
        app.goTo().goToHomePage();
        app.user().checkAdding(group);
        Users after = app.db().users();

        assertThat(after, equalTo(before));

        verifyUserListUI();
    }

}

