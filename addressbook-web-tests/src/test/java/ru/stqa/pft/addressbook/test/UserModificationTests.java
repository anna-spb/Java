package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

public class UserModificationTests extends TestBase {

    @Test
    public void testUserModification() {
        if(! app.getUserHelper().isThereAnyUser()){
            app.getUserHelper().createUser(new UserData("Anna", null, "Dedova",
                    null, null, "Tel-Aviv", "123456789", "test1"));
        }
        int before = app.getUserHelper().getUserCount();
        app.getUserHelper().selectUser();
        app.getUserHelper().initUserModification();
        app.getUserHelper().fillUserForm(new UserData("Anna", "Borisovna", "Dedova",
                "Sova", "Google", "Tel-Aviv", "123456789", "test1"), false);
        app.getUserHelper().submitModificationUser();
        app.getNavigationHelper().goToHomePage();
        int after = app.getUserHelper().getUserCount();
        Assert.assertEquals(after, before);
    }
}
