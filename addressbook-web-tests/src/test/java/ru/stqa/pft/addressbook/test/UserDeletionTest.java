package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

public class UserDeletionTest extends TestBase {

    @Test
    public void testUserDeletion() {
        if (!app.getUserHelper().isThereAnyUser()) {
            app.getUserHelper().createUser(new UserData("Anna", null, "Dedova",
                    null, "Google", "Tel-Aviv", "123456789", "test1"));
        }
        int before = app.getUserHelper().getUserCount();
        app.getUserHelper().selectUser(before - 1);
        app.getUserHelper().deleteSelectedUser();
        app.getUserHelper().closeAlert();
        app.getNavigationHelper().goToHomePage();
        int after = app.getUserHelper().getUserCount();
        Assert.assertEquals(after, before - 1);

    }
}
