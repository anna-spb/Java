package ru.stqa.pft.addressbook.test;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

public class UserDeletionTest extends TestBase {

    @Test
    public void testUserDeletion() {
        if (!app.getUserHelper().isThereAnyUser()) {
            app.getUserHelper().createUser(new UserData("Anna", null, "Dedova",
                    null, "Google", "Tel-Aviv", "123456789", "test1"));
        }
        app.getUserHelper().selectUser();
        app.getUserHelper().deleteSelectedUser();
        app.getUserHelper().closeAlert();
        app.getNavigationHelper().goToHomePage();

    }
}
