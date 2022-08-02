package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;

import java.io.File;
import java.util.Objects;

public class UserData {
    private int id = Integer.MAX_VALUE;
    @Expose
    private String firstName;
    private String middleName;
    @Expose
    private String lastName;
    private String nickname;
    private String company;
    @Expose
    private String address;
    @Expose
    private String homePhone;
    private String mobilePhone;
    private String workPhone;
    private String phone2;
    private String allPhones;
    private String email;
    private String email2;
    private String email3;
    private String allEmail;
    @Expose
    private String group;
    private File photo;


    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public String getPhone2() {
        return phone2;
    }

    public String getEmail() {
        return email;
    }

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }

    public String getAllEmail() {
        return allEmail;
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

    public String getHomePhone() {
        return homePhone;
    }

    public String getGroup() {
        return group;
    }

    public File getPhoto() {
        return photo;
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

    public UserData withGroup(String group) {
        this.group = group;
        return this;
    }

    public UserData withHomePhone(String phone) {
        this.homePhone = phone;
        return this;
    }

    public UserData withMobilePhone(String mobile) {
        this.mobilePhone = mobile;
        return this;
    }

    public UserData withWorkPhone(String work) {
        this.workPhone = work;
        return this;
    }

    public UserData withPhone2(String phone2) {
        this.phone2 = phone2;
        return this;
    }

    public UserData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public UserData withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public UserData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public UserData withAllEmail(String allEmail) {
        this.allEmail = allEmail;
        return this;
    }

    public UserData withPhoto(File photo) {
        this.photo = photo;
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
        return id == userData.id && Objects.equals(firstName, userData.firstName) && Objects.equals(lastName, userData.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName);
    }


}