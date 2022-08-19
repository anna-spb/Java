package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTests extends TestBase {


    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @BeforeTest
    public void ensurePreconditions() throws InterruptedException {
        if (app.db().users().size() == 1) {
            app.registration().registrationNewUser("userNew@localhost.localdomain", "userNew", "password");
        }
    }

    @Test
    public void testChangePassword() throws IOException, InterruptedException {
        app.user().loginAdmin();
        app.user().chooseManageUsers();
        Users user = app.db().users();
        UserData userPassword = user.iterator().next();
        String username = userPassword.getUsername();
        String email = userPassword.getEmail();
        String passwordNew = "passwordNew";
        app.user().chooseUser(username);
        app.user().resetPassword();

        List<MailMessage> mailMessages = app.mail().getMailMessages();
        String confirmationLink = app.user().findConfirmationLink(mailMessages, email);
        app.user().confirmAccount(confirmationLink, passwordNew, username);

        assertTrue(app.newSession().login(username, passwordNew));
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }

}
