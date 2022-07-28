package ru.stqa.pft.addressbook.test;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserCreationTest extends TestBase {


    @Test
    public void testUserCreation() {
        app.goTo().goToHomePage();
        Users before = app.user().all();
        UserData user = new UserData().withFirstName("Neta").withMiddleName("Dina").withLastName("Broshkina")
                .withNickname("Sova").withCompany("Google").withAddress("Tel-Aviv").withHomePhone("123456789").withGroup("test1");
        app.user().create(user);
        assertThat(app.user().count(), equalTo(before.size() + 1));
        Users after = app.user().all();

        assertThat(after, equalTo(
                before.withAdded(user.withId(after.stream().mapToInt((a) -> a.getId()).max().getAsInt()))));
    }

}

