package ru.stqa.pft.addressbook.test;

import com.google.gson.Gson;
import org.openqa.selenium.json.TypeToken;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserCreationTest extends TestBase {

    @DataProvider
    public Iterator<Object[]> validUsersFromJson() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/contacts.json"));
        String json = "";
        String line = reader.readLine();
        while (line != null) {
            json += line;
            line = reader.readLine();
        }
        Gson gson = new Gson();
        List<UserData> users = gson.fromJson(json, new TypeToken<List<UserData>>() {
        }.getType());

        return users.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();

    }


    @Test(dataProvider = "validUsersFromJson")
    public void testUserCreation(UserData user) {
        app.goTo().goToHomePage();
        Users before = app.user().all();
        // File photo = new File("src/test/resources/pic.JPG");
        app.user().create(user);
        assertThat(app.user().count(), equalTo(before.size() + 1));
        Users after = app.user().all();

        assertThat(after, equalTo(
                before.withAdded(user.withId(after.stream().mapToInt((a) -> a.getId()).max().getAsInt()))));

        verifyUserListUI();
    }
}
