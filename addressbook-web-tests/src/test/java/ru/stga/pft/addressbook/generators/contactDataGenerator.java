package ru.stga.pft.addressbook.generators;

import ru.stga.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class contactDataGenerator {

  public static void main(String[] args) throws IOException {
    int count = Integer.parseInt(args[0]);
    File file = new File(args[1]);

    List<ContactData> contacts = generateContacts(count);
    save(contacts, file);
  }

  private static void save(List<ContactData> contacts, File file) throws IOException {
    Writer writer = new FileWriter(file);
    for (ContactData contact : contacts) {
      writer.write(String.format("%s;%s;%s;%s;%s\n", contact.getFirstName(), contact.getLastName(), contact.getAddress(),
              contact.getEmail(), contact.getHomePhone()));
    }
    writer.close();
  }

  private static List<ContactData> generateContacts(int count) {
    List<ContactData> contacts = new ArrayList<ContactData>();
    for (int i = 0; i < count; i++) {
      contacts.add(new ContactData().withFirstName(String.format("FirstName %s", i))
              .withLastName(String.format("LastName %s", i))
              .withAddress(String.format("Address %s", i))
              .withEmail(String.format("Email %s", i))
              .withHomePhone(String.format("HomePhone %s", i)));
    }
    return contacts;
  }
}
