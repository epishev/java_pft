package ru.stga.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stga.pft.addressbook.model.ContactData;
import ru.stga.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      app.contact().create(new ContactData().withFirstName("test4").withLastName("test2").withMiddleName("MiddleName321").withAddress("test3")
              .withHomePhone("123456").withEmail("test@test.com").withGroup("test1"));
    }
  }

  @Test
  public void testContactModification() {
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    File photo = new File("src/test/resources/photo.jpeg");
    ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstName("test8").withLastName("test2").withMiddleName("MiddleName321")
            .withAddress("test3").withHomePhone("123456").withEmail("test@test.com").withPhoto(photo);
    app.contact().modify(contact);
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    verifyContactListInUI();

  }

}
