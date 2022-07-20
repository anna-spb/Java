package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class UserData {
    private int id;
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final String nickname;
    private final String company;
    private final String address;
    private final String phone;
    private String group;

    public UserData(int id, String firstName, String middleName, String lastName,
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
        this.id = 0;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.nickname = nickname;
        this.company = company;
        this.address = address;
        this.phone = phone;
        this.group = group;
    }


    public int getId() {
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

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName);
    }

    @Override
    public String toString() {
        return "UserData{}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserData userData = (UserData) o;
        return id == userData.id && Objects.equals(firstName, userData.firstName) && Objects.equals(lastName, userData.lastName);
    }


}