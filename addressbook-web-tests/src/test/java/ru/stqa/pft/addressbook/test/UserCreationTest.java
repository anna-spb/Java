package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

public class UserCreationTest extends TestBase {


    @Test
    public void testUserCreation() {
        int before = app.getUserHelper().getUserCount();

        app.getUserHelper().createUser(new UserData("Anna", null, "Dedova",
                null, "Google", "Tel-Aviv", "123456789","test3"));

        int after = app.getUserHelper().getUserCount();
        Assert.assertEquals(after, before +1);
    }

}

