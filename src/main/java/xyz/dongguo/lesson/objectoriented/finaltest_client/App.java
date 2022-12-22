package xyz.dongguo.lesson.objectoriented.finaltest_client;

import xyz.dongguo.lesson.objectoriented.finaltest_client.dataCollection.ClientDataCollection;

public class App {

  public static void main(String[] args) {

    ClientDataCollection clients = new ClientDataCollection();

    System.out.println("\nMethod 1 : Initialize the client list");
    clients.printClientArrayListWith4Clients();

    System.out.println("\nMethod 2  Delete John Abbott by SIN");
    clients.deleteJohnAbbottDem();

    System.out.println("\nMethod 3  Update Anthony Hopkins address");
    clients.updateAnthonyHopkinsAddressDem();

    System.out.println("\nMethod 4  Sort by family");
    clients.sortByFamilyDem();

    System.out.println("\nMethod 5  Sort by city");
    clients.sortByCityDem();

    System.out.println("\nMethod 6  Prevent duplicate user name");
    clients.preventDuplicateClientDem();

  }
}