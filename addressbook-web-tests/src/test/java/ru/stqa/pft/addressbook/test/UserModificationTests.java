package ru.stqa.pft.addressbook.test;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

public class UserModificationTests extends TestBase {

    @Test
    public void testUserModification() {
        app.getUserHelper().selectUser();
        app.getUserHelper().initUserModification();
        app.getUserHelper().fillUserForm(new UserData("Anna", "Borisovna", "Dedova",
                "Sova", "Google", "Tel-Aviv", "123456789"));
        app.getUserHelper().submitModificationUser();
        app.getNavigationHelper().returnToHomePage();
    }
}
