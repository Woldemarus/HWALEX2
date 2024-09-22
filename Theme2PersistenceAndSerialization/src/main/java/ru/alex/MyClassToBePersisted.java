package ru.alex;

import java.io.Serializable;
import java.util.Objects;

//Сделали класс для сериализации/десериализации
public class MyClassToBePersisted implements Serializable {
    private static final long serialVersionUID = 1L;

    private String profile;
    private String group;

    public MyClassToBePersisted(String profile, String group) {
        this.profile = profile;
        this.group = group;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyClassToBePersisted that = (MyClassToBePersisted) o;
        return Objects.equals(profile, that.profile) && Objects.equals(group, that.group);
    }

    @Override
    public int hashCode() {
        return Objects.hash(profile, group);
    }

    @Override
    public String toString() {
        return "MyClassToBePersisted{" +
                "profile='" + profile + '\'' +
                ", group='" + group + '\'' +
                '}';
    }
}
