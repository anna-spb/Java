package ru.stqa.pft.addressbook.test;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserPersonalsTest extends TestBase {

    @Test
    public void testUserPhones() {
        app.goTo().goToHomePage();
        UserData user = app.user().all().iterator().next();
        UserData userInfoFromEdit = app.user().InfoFromEdit(user);

        assertThat(user.getAllPhones(), equalTo(mergePhones(userInfoFromEdit)));
        assertThat(user.getAddress(), equalTo(mergeAddress(userInfoFromEdit)));
        assertThat(user.getAllEmail(), equalTo(mergeEmail(userInfoFromEdit)));
    }


    private Object mergePhones(UserData user) {
        return Arrays.asList(user.getHomePhone(), user.getMobilePhone(), user.getWorkPhone())
                .stream().filter((s) -> !s.equals(""))
                .map(UserPersonalsTest::cleaned)
                .collect(Collectors.joining("\n"));
    }

    private Object mergeAddress(UserData user) {
        return Arrays.asList(user.getAddress())
                .stream().filter((s) -> !s.equals(""))
                .collect(Collectors.joining("\n"));
    }

    private Object mergeEmail(UserData user) {
        return Arrays.asList(user.getEmail(), user.getEmail2(), user.getEmail3())
                .stream().filter((s) -> !s.equals(""))
                .map(UserPersonalsTest::cleaned)
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }


}
