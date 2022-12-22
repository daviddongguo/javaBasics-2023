package xyz.dongguo.lesson.objectoriented.finaltest_client.dataCollection;

import java.util.ArrayList;
import java.util.List;
import xyz.dongguo.lesson.objectoriented.finaltest_client.entity.Address;
import xyz.dongguo.lesson.objectoriented.finaltest_client.entity.CityComparator;
import xyz.dongguo.lesson.objectoriented.finaltest_client.entity.Client;

public class ClientDataCollection {

  private final List<Client> clientArrayList = new ArrayList<>(4);
  private final int JOHN_ABBOTT_SIN = 222;
  private final String ANTHONY_HOPKINS_FIRST_NAME = "Hopkins";
  private final String ANTHONY_HOPKINS_LAST_NAME = "Anthony";
  private final String ANTHONY_USER_NAME = "anthony";

  /**
   * Method 1 create 4 clients, add them to the ArrayList and  print the ArrayList.
   */
  public void printClientArrayListWith4Clients() {
    initializeClientArrayListWith4Clients();
    printClientArrayList();
  }

  private void initializeClientArrayListWith4Clients() {
    Address address_01 = new Address(9336, "Civic Center Drive", "Beverly Hills", "California", "US", "456 123");
    Address address_02 = new Address(1840, "Century Park East", "Los Angeles", "California", "US", " 987 543");
    Address address_03 = new Address(1010, "Wellington St", "Ottawa", "Ontario", "Canada", "K1A 0A9");
    Address address_04 = new Address(275, "Rue Lakeshore Road", "Montreal", "Quebec", "Canada", "H9X 3L9");
    address_04.setApartmentNumber(21);

    clientArrayList.addAll(List.of(
       new Client(444, ANTHONY_HOPKINS_FIRST_NAME, ANTHONY_HOPKINS_LAST_NAME, ANTHONY_USER_NAME, address_01),
       new Client(333, "Tom", "Sawyer", "tom", address_02),
       new Client(111, "Adam", "Abbott", "adam", address_03),
       new Client(JOHN_ABBOTT_SIN, "John", "Abbott", "john", address_04)
    ));
  }

  private void printClientArrayList() {
    int size = clientArrayList.size();
    System.out.printf("%nThere %d client(s) in the clientArrayList as below:%n", size);
    for (Client currentClient : clientArrayList) {
      System.out.println(currentClient);
    }
  }

  /**
   * Method 2 Delete John Abbott by SIN and Print ArrayList of clients in ClientDataCollection again and show John
   * Abbott does not exist
   */
  public void deleteJohnAbbottDem() {
    deleteClientBySIN(JOHN_ABBOTT_SIN);
    printClientArrayList();
  }

  private boolean deleteClientBySIN(int SIN) {
    Client clientToDelete = findBySIN(SIN);
    if (clientToDelete == null) {
      return false;
    }
    clientArrayList.remove(clientToDelete);
    return true;
  }

  private Client findBySIN(int SIN) {
    for (Client currentClient : clientArrayList) {
      if (currentClient.getSIN() == SIN) {
        return currentClient;
      }
    }
    return null;
  }

  /**
   * Method 3
   * <p> Update Anthony Hopkins address to Montreal, Quebec, Canada, 2022 Saint Cathrin, Postal Code 123 456
   * and Print ArrayList of clients in ClientDataCollection again and show updated address</p>
   */
  public void updateAnthonyHopkinsAddressDem() {
    Client clientAnthonyHopkins = findByFirstnameAndLastName(ANTHONY_HOPKINS_FIRST_NAME, ANTHONY_HOPKINS_LAST_NAME);
    Address newAddressForAnthony = new Address(2022, "Saint Cathrin", "Montreal", "Quebec", "Canada", "123 456");
    if (clientAnthonyHopkins == null) {
      return;
    }
    clientAnthonyHopkins.setAddress(newAddressForAnthony);
    printClientArrayList();
  }

  private Client findByFirstnameAndLastName(String firstName, String lastname) {
    for (Client currentClient : clientArrayList) {
      if (currentClient.getFirstName().trim().toLowerCase().equals(firstName.trim().toLowerCase())
         && currentClient.getLastName().trim().toLowerCase().equals(lastname.trim().toLowerCase())) {
        return currentClient;
      }
    }
    return null;
  }

  /**
   * Method 4 Use Comparable to Sort DataCollection ArrayList by client family
   */
  public void sortByFamilyDem() {
    clientArrayList.sort((a, b) -> a.compareTo(b));
    printClientArrayList();
  }

  /**
   * Method 5
   * <p>
   * Use Comparator to Sort DataCollection ArrayList by city
   * </p>
   */
  public void sortByCityDem() {
    clientArrayList.sort(new CityComparator());
    printClientArrayList();
  }

  public void preventDuplicateClientDem() {
    String dulicateUsername = ANTHONY_USER_NAME;
    boolean isExistedUsername = findClientByUserName(dulicateUsername) != null;
    if (isExistedUsername) {
      System.out.printf("%n%s already exist.", dulicateUsername);
    } else {
      Address mockAddress = new Address(123, "mockStree", "mockCity", "mockProvince", "mockCountry", "mok cod");
      Client duplicateUserNameClient = new Client(333, "mockFirstName", "mockLastName", dulicateUsername,
         mockAddress);
      clientArrayList.add(duplicateUserNameClient);
    }

    printClientArrayList();
  }

  private Client findClientByUserName(String userName) {
    for (Client currentClient : clientArrayList) {
      if (currentClient.getUserName().trim().equalsIgnoreCase(userName.trim())) {
        return currentClient;
      }
    }
    return null;
  }

  private boolean addClientByUserName(Client clientToAdd) {
    boolean isExistedUsername = findClientByUserName(clientToAdd.getUserName()) != null;
    if (isExistedUsername) {
      return false;
    }
    clientArrayList.add(clientToAdd);
    return true;
  }

}
