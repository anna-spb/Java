package ru.stqa.pft.addressbook.test;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

public class UserCreationTest extends TestBase {


    @Test
    public void testUserCreation() {

        app.getUserHelper().createUser(new UserData("Anna", null, "Dedova",
                null, "Google", "Tel-Aviv", "123456789","test3"));
    }

}

