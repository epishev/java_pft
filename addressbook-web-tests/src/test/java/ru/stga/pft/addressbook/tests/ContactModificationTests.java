package ru.stga.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stga.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.getNavigationHelper().goToHomePage();
    int before = app.getContactHelper().getContactCount();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("test1", "test2", "test3", "123456", "test@test.com", "test1"));
    }
    app.getContactHelper().selectContact(before - 1);
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("test1", "test2", "test3", "123456", "test@test.com", null),false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before);


  }
}
