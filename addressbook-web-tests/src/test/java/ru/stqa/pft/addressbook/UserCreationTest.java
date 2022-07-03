package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class UserCreationTest extends TestBase {


    @Test
    public void testUserCreation() {
        addNewUser();
        fillUserForm(new UserData("Anna", "Borisovna", "Dedova",
                "Sova", "Google", "Tel-Aviv", "123456789"));
        submitNewUser();
        returnToHomePage();
    }

}

