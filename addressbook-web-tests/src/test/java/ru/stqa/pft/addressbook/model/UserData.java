package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class UserData {
    private String id;
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final String nickname;
    private final String company;
    private final String address;
    private final String phone;
    private String group;

    public UserData(String id, String firstName, String middleName, String lastName,
                    String nickname, String company, String address, String phone, String group) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.nickname = nickname;
        this.company = company;
        this.address = address;
        this.phone = phone;
        this.group = group;
    }

    public UserData(String firstName, String middleName, String lastName,
                    String nickname, String company, String address, String phone, String group) {
        this.id = null;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.nickname = nickname;
        this.company = company;
        this.address = address;
        this.phone = phone;
        this.group = group;
    }


    public String getId() {
        return id;
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

    @Override
    public String toString() {
        return "UserData{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserData userData = (UserData) o;
        return Objects.equals(id, userData.id) && Objects.equals(firstName, userData.firstName) && Objects.equals(lastName, userData.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName);
    }

}