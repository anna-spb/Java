package ru.stqa.pft.addressbook.test;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

public class UserCreationTest extends TestBase {


    @Test
    public void testUserCreation() {
        app.getUserHelper().addNewUser();
        app.getUserHelper().fillUserForm(new UserData("Anna", "Borisovna", "Dedova",
                "Sova", "Google", "Tel-Aviv", "123456789", "test1"), true);
        app.getUserHelper().submitNewUser();
        app.getNavigationHelper().goToHomePage();
    }

}

