package ru.stqa.pft.addressbook.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class UserDeletionTest extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().goToHomePage();
        if (app.user().all().size() == 0) {
            app.user().create(new UserData().withFirstName("Anna").withLastName("Dedova").withCompany("Google")
                    .withAddress("Tel-Aviv").withHomePhone("123456789").withGroup("test1"));
        }
    }

    @Test
    public void testUserDeletion() {
        Users before = app.user().all();
        UserData deletedUser = before.iterator().next();
        app.user().delete(deletedUser);
        assertThat(app.user().count(), equalTo(before.size() - 1));
        Users after = app.user().all();

        assertThat(after, equalTo(before.without(deletedUser)));
    }
}
