package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.List;
import java.util.Set;

public class UserDeletionTest extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().goToHomePage();
        if (app.user().all().size() == 0) {
            app.user().create(new UserData().withFirstName("Anna").withLastName("Dedova").withCompany("Google")
                    .withAddress("Tel-Aviv").withPhone("123456789").withGroup("test1"));
        }
    }

    @Test
    public void testUserDeletion() {
        Set<UserData> before = app.user().all();
        UserData deletedUser = before.iterator().next();
        app.user().delete(deletedUser);
        Set<UserData> after = app.user().all();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(deletedUser);
        Assert.assertEquals(before, after);
    }
}
