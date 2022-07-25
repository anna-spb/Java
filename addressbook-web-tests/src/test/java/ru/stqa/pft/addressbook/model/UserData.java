package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class UserData {
    private int id = Integer.MAX_VALUE;
    private String firstName;
    private String middleName;
    private String lastName;
    private String nickname;
    private String company;
    private String address;
    private String phone;
    private String group;


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


    public UserData withId(int id) {
        this.id = id;
        return this;
    }
    public UserData withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }
    public UserData withMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }
    public UserData withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
    public UserData withNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }
    public UserData withCompany(String company) {
        this.company = company;
        return this;
    }
    public UserData withAddress(String address) {
        this.address = address;
        return this;
    }
    public UserData withPhone(String phone) {
        this.phone = phone;
        return this;
    }
    public UserData withGroup(String group) {
        this.group = group;
        return this;
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
        return Objects.equals(firstName, userData.firstName) && Objects.equals(lastName, userData.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }
}