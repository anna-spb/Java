package ru.stqa.pft.addressbook;
import org.testng.annotations.Test;

public class UserDeletionTest extends TestBase {

    @Test
    public void testUserDeletion() {
        selectUser();
        deleteSelectedUser();
        closeAlert();

    }
}
