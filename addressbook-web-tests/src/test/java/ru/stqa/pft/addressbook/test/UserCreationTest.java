package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;
import java.util.Set;

public class UserCreationTest extends TestBase {


    @Test
    public void testUserCreation() {
        app.goTo().goToHomePage();
        Set<UserData> before = app.user().all();
        UserData user = new UserData().withFirstName("Neta").withMiddleName("Dina").withLastName("Broshkina")
                .withNickname("Sova").withCompany("Google").withAddress("Tel-Aviv").withPhone("123456789").withGroup("test1");
        app.user().create(user);

        Set<UserData> after = app.user().all();
        Assert.assertEquals(after.size(), before.size() + 1);

        user.withId(after.stream().mapToInt((a) -> a.getId()).max().getAsInt());
        before.add(user);
        Assert.assertEquals(before, after);
    }

}

