package xyz.dongguo.lesson.objectoriented.finaltest_client.entity;

public class Address {

  private int apartmentNumber = 0;
  private int buildingNumber;
  private String street;
  private String city;
  private String province;
  private String country;
  private String postalCode;

  public Address(int buildingNumber, String street, String city, String province, String country, String postalCode) {
    this.buildingNumber = buildingNumber;
    this.street = street;
    this.city = city;
    this.province = province;
    this.country = country;
    this.postalCode = postalCode;
  }

  public int getApartmentNumber() {
    return apartmentNumber;
  }

  public void setApartmentNumber(int apartmentNumber) {
    this.apartmentNumber = apartmentNumber;
  }

  public int getBuildingNumber() {
    return buildingNumber;
  }

  public void setBuildingNumber(int buildingNumber) {
    this.buildingNumber = buildingNumber;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  @Override
  public String toString() {
    String apartmentNumberString = apartmentNumber == 0 ? "" : "apartmentNumber=" + apartmentNumber + ", ";
    return "Address{" +
       apartmentNumberString +
       "buildingNumber=" + buildingNumber +
       ", street='" + street + '\'' +
       ", city='" + city + '\'' +
       ", province='" + province + '\'' +
       ", country='" + country + '\'' +
       ", postalCode='" + postalCode + '\'' +
       '}';
  }

  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }
}
