package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.HashSet;
import java.util.List;

public class UserCreationTest extends TestBase {


    @Test
    public void testUserCreation() {

        List<UserData> before = app.getUserHelper().getUserList();
        UserData user = new UserData("Netochka", "Borisovna",
                "Broshkina", "Sova", "Google", "Tel-Aviv", "123456789", "test1");
        app.getUserHelper().createUser(user);

        List<UserData> after = app.getUserHelper().getUserList();
        Assert.assertEquals(after.size(), before.size() + 1);

        user.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
        before.add(user);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }

}

