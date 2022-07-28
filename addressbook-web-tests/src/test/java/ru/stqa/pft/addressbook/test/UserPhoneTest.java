package ru.stqa.pft.addressbook.test;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class UserPhoneTest extends TestBase {

    @Test
    public void testUserPhones() {
        app.goTo().goToHomePage();
        UserData user = app.user().all().iterator().next();
        UserData userInfoFromEdit = app.user().InfoFromEdit(user);

        assertThat(user.getAllPhones(), equalTo(mergePhones(userInfoFromEdit)));
    }


    private Object mergePhones(UserData user) {
        return Arrays.asList(user.getHomePhone(), user.getMobilePhone(), user.getWorkPhone())
                .stream().filter((s) -> !s.equals(""))
                .map(UserPhoneTest :: cleaned)
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }


}
