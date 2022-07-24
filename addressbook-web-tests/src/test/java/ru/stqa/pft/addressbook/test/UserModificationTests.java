package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.Comparator;
import java.util.List;

public class UserModificationTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.getNavigationHelper().goToHomePage();
        if (!app.getUserHelper().isThereAnyUser()) {
            app.getUserHelper().createUser(new UserData("Anna", null,
                    "Dedova", null, "Google", "Tel-Aviv", "123456789", "test1"));
        }
    }

    @Test
    public void testUserModification() {
        List<UserData> before = app.getUserHelper().getUserList();
        int index = before.size() - 1;
        UserData user = new UserData(before.get(index).getId(), "Netochka", "Borisovna",
                "Broshkina", "Sova", "Google", "Tel-Aviv", "123456789", "test1");
        app.getUserHelper().modifyUser(index, user);
        List<UserData> after = app.getUserHelper().getUserList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(user);

        Comparator<? super UserData> byId = (u1, u2) -> Integer.compare(u1.getId(), u2.getId());
        before.sort(byId);
        after.sort(byId);

        Assert.assertEquals(after, before);
    }


}
