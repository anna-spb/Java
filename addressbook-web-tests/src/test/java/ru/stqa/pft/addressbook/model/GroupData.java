package ru.stqa.pft.addressbook.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.util.Objects;


@XStreamAlias("group")
public class GroupData {
    @XStreamOmitField
    private int id =  Integer.MAX_VALUE;;
    private String name;
    private String header;
    private String footer;


    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupData groupData = (GroupData) o;
        return id == groupData.id && Objects.equals(name, groupData.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public String getHeader() {
        return header;
    }
    public String getFooter() {
        return footer;
    }

    public GroupData withId(int id) {
        this.id = id;
        return this;
    }
       public GroupData withName(String name) {
        this.name = name;
           return this;
    }
    public GroupData withHeader(String header) {
        this.header = header;
        return this;
    }
    public GroupData withFooter(String footer) {
        this.footer = footer;
        return this;
    }


    @Override
    public String toString() {
        return "GroupData{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

}
