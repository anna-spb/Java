package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import java.util.List;

public class UserHelper extends HelperBase {


    public UserHelper(ApplicationManager app) {
        super(app);
    }


    public void login(String username, String password) {
        wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
        type(By.name("username"), username);
        click(By.cssSelector("input[type='submit'"));
        type(By.name("password"), password);
        click(By.cssSelector("input[type='submit'"));

    }

    public void loginAdmin() {

        login(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
    }

    public void confirmAccount(String confirmationLink, String password, String user) {
        wd.get(confirmationLink);
        type(By.name("realname"), user);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.xpath("//button[@type='submit']"));
    }

    public void manageUsers(By locator) {
        app.user().click(locator);
        app.user().click(By.linkText("Manage Users"));
    }

    public void chooseManageUsers() {
        app.user().manageUsers(By.xpath("//div[@id='sidebar']/ul/li[6]/a/i"));
    }

    public void chooseUser(String username) {
        app.user().click(By.linkText(String.format("%s", username)));
    }

    public void resetPassword() {
        app.user().click(By.cssSelector("input[value='Reset Password'"));
    }

    public String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

}
