package ru.stga.pft.addressbook;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() {

    goToHomePage();
    selectContact();
    deleteSelectedContacts();
    acceptDialogWindow();
    goToHomePage();

  }

}
