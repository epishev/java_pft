package ru.stga.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stga.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() {
    app.goTo().homePage();
    Set<ContactData> before = app.contact().all();
    ContactData contact = new ContactData()
            .withFirstName("test4").withLastName("test2").withAddress("test3").withPhoneNumber("123456")
            .withEmail("test@test.com").withGroup("test1");
    app.contact().create(contact);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);


  contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(before,after);
  }
}
