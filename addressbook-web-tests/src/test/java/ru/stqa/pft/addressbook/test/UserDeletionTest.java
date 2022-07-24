package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;
import java.util.List;

public class UserDeletionTest extends TestBase {
    @BeforeMethod
    public void ensurePreconditions(){
        app.getNavigationHelper().goToHomePage();
        if (!app.getUserHelper().isThereAnyUser()) {
            app.getUserHelper().createUser(new UserData( "Anna", null,
                    "Dedova", null, "Google", "Tel-Aviv", "123456789", "test1"));
        }
    }
    @Test
    public void testUserDeletion() {
        List<UserData> before = app.getUserHelper().getUserList();
        int index = before.size()-1;
        app.getUserHelper().selectUser(index);
        app.getUserHelper().deleteSelectedUser();
        app.getUserHelper().closeAlert();
        app.getNavigationHelper().goToHomePage();
        List<UserData> after = app.getUserHelper().getUserList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(index);
        Assert.assertEquals(before, after);

    }
}
