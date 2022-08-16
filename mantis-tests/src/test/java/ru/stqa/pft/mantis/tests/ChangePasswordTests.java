package ru.stqa.pft.mantis.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.MailMessage;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTests extends TestBase {


    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testChangePassword() throws IOException, InterruptedException {
        // зашли админом
        app.login().loginAdmin();
        //перешли на страницу управления
        app.login().click(By.xpath("//div[@id='sidebar']/ul/li[6]/a/i"));

        //перешли на страницу управления юзером
        app.login().click(By.linkText("Управление пользователями"));


        String email1 = "anna@localhost.localdomain";
        String username = "anna";
        String password1 = "root1";
        app.login().click(By.linkText(String.format("%s", username)));
        app.login().click(By.cssSelector("input[value='Сбросить пароль'"));

        //проверить почту

        List<MailMessage> mailMessages = app.mail().waitForMail(1, 100000);
        String confirmationLink = findConfirmationLink(mailMessages, email1);
        app.registration().finish(confirmationLink, password1);
        assertTrue(app.newSession().login(username, password1));
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
