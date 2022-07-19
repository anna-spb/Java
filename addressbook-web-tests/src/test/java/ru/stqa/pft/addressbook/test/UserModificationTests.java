package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.List;

public class UserModificationTests extends TestBase {

    @Test
    public void testUserModification() {
        if(! app.getUserHelper().isThereAnyUser()){
            app.getUserHelper().createUser(new UserData("Anna", null, "Dedova",
                    null, null, "Tel-Aviv", "123456789", "test1"));
        }
        List<UserData> before = app.getUserHelper().getUserList();
        app.getUserHelper().selectUser(before.size() - 1);
        app.getUserHelper().initUserModification();
        app.getUserHelper().fillUserForm(new UserData("Anna", "Borisovna", "Dedova",
                "Sova", "Google", "Tel-Aviv", "123456789", "test1"), false);
        app.getUserHelper().submitModificationUser();
        app.getNavigationHelper().goToHomePage();
        List<UserData> after = app.getUserHelper().getUserList();
        Assert.assertEquals(after.size(), before.size() );
    }
}
