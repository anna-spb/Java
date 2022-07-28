package ru.stqa.pft.addressbook.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class UserModificationTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().goToHomePage();
        if (app.user().all().size() == 0) {
            app.user().create(new UserData().withFirstName("Anna").withLastName("Dedova").withCompany("Google")
                    .withAddress("Tel-Aviv").withHomePhone("123456789").withGroup("test1"));
        }
    }

    @Test
    public void testUserModification() {
        Users before = app.user().all();
        UserData modifiedUser = before.iterator().next();
        UserData user = new UserData().withId(modifiedUser.getId()).withFirstName("Madam").withLastName("Broshkina")
                .withNickname("Sova").withCompany("Google").withGroup("test1");
        app.user().modify(user);
        assertThat(app.user().count(), equalTo(before.size()));
        Users after = app.user().all();

        assertThat(after, equalTo(before.without(modifiedUser).withAdded(user)));
    }


}
