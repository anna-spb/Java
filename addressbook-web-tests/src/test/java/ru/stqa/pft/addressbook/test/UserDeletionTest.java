package ru.stqa.pft.addressbook.test;
import org.testng.annotations.Test;

public class UserDeletionTest extends TestBase {

    @Test
    public void testUserDeletion() {
        app.selectUser();
        app.deleteSelectedUser();
        app.closeAlert();
        app.returnToHomePage();

    }
}
