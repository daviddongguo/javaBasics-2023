package xyz.dongguo.lesson.objectoriented.finaltest_client.entity;

public class Client extends Person {

  private String userName;
  private String password;
  private Address address;

  public Client(int SIN, String firstName, String lastName, String userName, Address address) {
    super(SIN, firstName, lastName);
    this.userName = userName;
    this.address = address;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  @Override
  public String toString() {
    return "Client{" +
       "userName='" + userName + '\'' +
       ", SIN='" + getSIN() + '\'' +
       ", firstName='" + getFirstName() + '\'' +
       ", lastname='" + getLastName() + '\'' +
       ", address=" + address +
       '}';
  }

}
