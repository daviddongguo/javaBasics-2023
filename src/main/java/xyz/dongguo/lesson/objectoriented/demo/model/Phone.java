package xyz.dongguo.lesson.objectoriented.demo.model;

public class Phone {
    int countryCode;
    int areaCode;
    int operatorCode;
    int number;

    public Phone(int countryCode, int areaCode, int operatorCode, int number) {
        this.countryCode = countryCode;
        this.areaCode = areaCode;
        this.operatorCode = operatorCode;
        this.number = number;
    }

    public int getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(int countryCode) {
        this.countryCode = countryCode;
    }

    public int getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(int areaCode) {
        this.areaCode = areaCode;
    }

    public int getOperatorCode() {
        return operatorCode;
    }

    public void setOperatorCode(int operatorCode) {
        this.operatorCode = operatorCode;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Phone: " +
                "countryCode=" + countryCode +
                ", areaCode=" + areaCode +
                ", operatorCode=" + operatorCode +
                ", number=" + number +
                " ";

    }
}
