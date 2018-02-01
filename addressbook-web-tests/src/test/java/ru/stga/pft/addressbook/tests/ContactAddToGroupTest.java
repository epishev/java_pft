package ru.stga.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stga.pft.addressbook.model.ContactData;
import ru.stga.pft.addressbook.model.Contacts;
import ru.stga.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddToGroupTest extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }

    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      app.contact().create(new ContactData().withFirstName("test4").withLastName("test2").withMiddleName("MiddleName321").withAddress("test3")
              .withHomePhone("123456").withEmail("test@test.com"));
    }
  }

  @Test
  public void testContactAddToGroup() {
    Contacts dbContacts = app.db().contacts();
    Contacts withoutGroupsContacts = contactsWithoutGroups(dbContacts);
    ContactData selectedContact = withoutGroupsContacts.iterator().next();

  }

  public Contacts contactsWithoutGroups(Contacts contacts) {
    Contacts contactsWithoutGroups = new Contacts();
    for (ContactData contact : contacts) {
      if (contact.getGroups().size() == 0) {
        contactsWithoutGroups.add(new ContactData().withId(contact.getId()).withFirstName(contact.getFirstName()));
      }
      }
      return contactsWithoutGroups;
  }


}
