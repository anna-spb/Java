package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.HashSet;
import java.util.List;

public class UserModificationTests extends TestBase {

    @Test
    public void testUserModification() {
        if(! app.getUserHelper().isThereAnyUser()){
            app.getUserHelper().createUser(new UserData("Anna", null,
                    "Dedova", null, null, "Tel-Aviv", "123456789", "test1"));
        }
        List<UserData> before = app.getUserHelper().getUserList();
        app.getUserHelper().selectUser(before.size() - 1);
        app.getUserHelper().initUserModification(before.size() - 1);
        UserData user = new UserData( before.get(before.size()-1).getId(),"Netochka", "Borisovna",
                "Broshkina", "Sova", "Google", "Tel-Aviv", "123456789", "test1");
        app.getUserHelper().fillUserForm(user, false);
        app.getUserHelper().submitModificationUser();
        app.getNavigationHelper().goToHomePage();
        List<UserData> after = app.getUserHelper().getUserList();
        Assert.assertEquals(after.size(), before.size() );

        before.remove(before.size() - 1);
        before.add(user);
        Assert.assertEquals(new HashSet<Object>(after),new HashSet<Object>(before));
    }
}
