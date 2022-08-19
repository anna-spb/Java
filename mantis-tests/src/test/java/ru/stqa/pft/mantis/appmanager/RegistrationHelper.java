package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import java.util.List;

public class RegistrationHelper extends HelperBase {

    public RegistrationHelper(ApplicationManager app) {
        super(app);
    }

    public void start(String username, String email) {
        wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
        type(By.name("username"), username);
        type(By.name("email"), email);
        click(By.cssSelector("input[type='submit'"));


    }

    public void submitLink(String email, String user, String password) throws InterruptedException {
        List<MailMessage> mailMessages = app.mail().waitForMail(2, 1000);
        String confirmationLink = findConfirmationLink(mailMessages, email);
        app.user().confirmAccount(confirmationLink, password, user);
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findAny().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }
    public void registrationNewUser(String email, String user, String password) throws InterruptedException {
       start(user, email);
       submitLink(email, user, password);

    }
}
