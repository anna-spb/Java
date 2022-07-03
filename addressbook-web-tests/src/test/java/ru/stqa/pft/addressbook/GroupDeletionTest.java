package ru.stqa.pft.addressbook;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class GroupDeletionTest extends TestBase {

    @Test
    public void testGroupDeletion() {
       goToGroupPage();
        selectGroup();
        deleteSelectedGroup();
        returnToGroupPage();
    }


    private void deleteSelectedGroup() {
        wd.findElement(By.xpath("//input[5]")).click();
    }

    private void selectGroup() {
        wd.findElement(By.name("selected[]")).click();
    }
}


