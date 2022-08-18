package ru.stqa.pft.mantis.tests;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
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
        app.login().loginAdmin();
        app.login().manageUsers(By.xpath("//div[@id='sidebar']/ul/li[6]/a/i"));
        Users user = app.db().users();
        UserData userPassword = user.iterator().next();
        String username = userPassword.getUsername();
        String email = userPassword.getEmail();
        String passwordNew = "root1";

        app.login().click(By.linkText(String.format("%s", username)));
        app.login().click(By.cssSelector("input[value='Reset Password'"));

        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String confirmationLink = findConfirmationLink(mailMessages, email);
        app.registration().finish(confirmationLink, passwordNew, username);

        assertTrue(app.newSession().login(username, passwordNew));
    }


    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }

}
