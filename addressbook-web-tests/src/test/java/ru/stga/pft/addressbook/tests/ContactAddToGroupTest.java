package  ru.stga.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stga.pft.addressbook.model.ContactData;
import ru.stga.pft.addressbook.model.Contacts;
import ru.stga.pft.addressbook.model.GroupData;
import ru.stga.pft.addressbook.model.Groups;

import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddToGroupTest extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0){
      app.goTo().homePage();
      app.contact().create(new ContactData().withFirstName("test4").withLastName("test2").withMiddleName("MiddleName321").withAddress("test3")
              .withHomePhone("123456").withEmail("test@test.com"));
    }
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }
  }


  @Test
  public void testAddContactToGroup() {
    Contacts listContacts = app.db().contacts();
    ContactData selectedContact = listContacts.iterator().next();
    Groups listGroups = app.db().groups();
    GroupData selectedGroup = listGroups.iterator().next();
    app.goTo().homePage();
    if (!selectedContact.getGroups().isEmpty() && selectedContact.getGroups().contains(selectedGroup)) {
      app.contact().removeContactFromGroup(selectedContact, selectedGroup);
      assertThat(selectedContact.getGroups().without(selectedGroup), CoreMatchers.equalTo(app.db().contacts().stream().
              filter((a)->a.getId() == selectedContact.getId()).collect(Collectors.toList()).get(0).getGroups()));
      app.goTo().homePage();
    }
    app.contact().selectDisplayGroup("[all]");
    app.contact().addContactToGroup(selectedContact, selectedGroup);
    assertThat(selectedContact.getGroups().withAdded(selectedGroup), CoreMatchers.equalTo(app.db().contacts().stream().
            filter((a)->a.getId() == selectedContact.getId()).collect(Collectors.toList()).get(0).getGroups()));
  }



}