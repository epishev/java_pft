package ru.stqa.pft.mantis.tests;

import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.DbMantisUsers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;


public class ChangePasswordTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() throws IOException, MessagingException {
    List<DbMantisUsers> users = app.db().users();
    DbMantisUsers randomUser = app.db().randomUser(users, 90);
    if (randomUser == null){
      long now = System.currentTimeMillis();
      String user = String.format("user%s",now);
      String password = "password";
      String email = String.format("user%s@localhost",now);
      app.james().createUser(user, password);
      app.registration().start(user, email);
      List<MailMessage> mailMessages = app.james().waitForMail(user, password, 60000);
      String confirmationLink = findConfirmationLink(mailMessages, email);
      app.registration().finish(confirmationLink, password);
      app.newSession().login(user, password);
      assertTrue(app.newSession().login(user,password));
    }
  }

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  @Test
  public void testChangeUserPassword() throws IOException, MessagingException {
    List<DbMantisUsers> users = app.db().users();
    DbMantisUsers user = app.db().randomUser(users, 90);
    long now = System.currentTimeMillis();
    String username = user.getUsername();
    String password = String.format("password%s", now);
    String email = username + "@localhost";
    app.james().deleteUser(username);
    app.james().createUser(username, password);
    app.james().drainEmail(username, password);
    app.testHelper().login(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
    app.testHelper().navigateToUsersPage();
    app.testHelper().resetPassword(user.getUsername());
    List<MailMessage> mailMessages = app.james().waitForMail(username, password, 60000);
    String confirmationLink = findConfirmationLink(mailMessages, email);
    app.registration().finish(confirmationLink, password);
    assertTrue(app.newSession().login(username, password));
  }
}