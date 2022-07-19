package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.List;

public class UserDeletionTest extends TestBase {

    @Test
    public void testUserDeletion() {
        if (!app.getUserHelper().isThereAnyUser()) {
            app.getUserHelper().createUser(new UserData("Anna", null, "Dedova",
                    null, "Google", "Tel-Aviv", "123456789", "test1"));
        }
        List<UserData> before = app.getUserHelper().getUserList();
        app.getUserHelper().selectUser(before.size() - 1);
        app.getUserHelper().deleteSelectedUser();
        app.getUserHelper().closeAlert();
        app.getNavigationHelper().goToHomePage();
        List<UserData> after = app.getUserHelper().getUserList();
        Assert.assertEquals(after.size(), before.size() - 1);

    }
}
