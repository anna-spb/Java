package ru.stqa.pft.addressbook.model;

public class UserData {
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final String nickname;
    private final String company;
    private final String address;
    private final String phone;
    private String group;

    public UserData(String firstName, String middleName, String lastName,
                    String nickname, String company, String address, String phone, String group) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.nickname = nickname;
        this.company = company;
        this.address = address;
        this.phone = phone;
        this.group = group;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNickname() {
        return nickname;
    }

    public String getCompany() {
        return company;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getGroup() {
        return group;
    }
}
