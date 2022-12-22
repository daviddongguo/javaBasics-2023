package xyz.dongguo.lesson.objectoriented.finaltest_client.entity;

import java.util.Comparator;

public class CityComparator implements Comparator<Client> {

  @Override
  public int compare(Client o1, Client o2) {
    Address o1Address = o1.getAddress();
    Address o2Address = o2.getAddress();
    return o1Address.getCity().trim().toLowerCase().compareTo(o2Address.getCity().trim().toLowerCase());
  }
}
