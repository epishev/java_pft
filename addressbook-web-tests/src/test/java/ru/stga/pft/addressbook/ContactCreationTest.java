package ru.stga.pft.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() {

    goToHomePage();
    initContactCreation();
    fillContactForm(new ContactData("test1", "test2", "test3", "123456", "test@test.com"));
    submitContactCreation();
    returnToHomePage();

  }
}
