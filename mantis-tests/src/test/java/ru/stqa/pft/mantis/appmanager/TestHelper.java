package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class TestHelper extends HelperBase{
  public TestHelper (ApplicationManager app) {super(app);}


  public void login(String username, String password) {
    wd.get(app.getProperty("web.baseUrl"));
    type(By.name("username"), username);
    click(By.cssSelector("input[type='submit'"));
    type(By.name("password"), password);
    click(By.cssSelector("input[type='submit'"));
  }

  public void navigateToUsersPage() {
    wd.get(app.getProperty("web.baseUrl") + "manage_overview_page.php");
    click(By.linkText("Управление пользователями"));
  }

  public void resetPassword(String username){
    click(By.linkText(username));
    click(By.cssSelector("input[value='Сбросить пароль'"));
  }

  public void gotoLink(String link) {wd.get(link);}
}