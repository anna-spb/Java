package ru.stqa.pft.addressbook.test;
import org.testng.annotations.Test;

public class UserDeletionTest extends TestBase {

    @Test
    public void testUserDeletion() {
        app.getUserHelper().selectUser();
        app.getUserHelper().deleteSelectedUser();
        app.getUserHelper().closeAlert();
        app.getNavigationHelper().returnToHomePage();

    }
}
