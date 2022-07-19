package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.List;

public class UserCreationTest extends TestBase {


    @Test
    public void testUserCreation() {

        List<UserData> before = app.getUserHelper().getUserList();
        app.getUserHelper().createUser(new UserData("Anna", null,
                "Dedova", null, "Google", "Tel-Aviv", "123456789", "test3"));

        List<UserData> after = app.getUserHelper().getUserList();
        Assert.assertEquals(after.size(), before.size() +1);
    }

}

