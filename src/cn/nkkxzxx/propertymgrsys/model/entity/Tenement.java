package cn.nkkxzxx.propertymgrsys.model.entity;

import java.util.Objects;

public class Tenement {
    private String name;
    private String phoneNumber;

    /**
     * 构造方法
     */
    public Tenement() {
    }

    public Tenement(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    /**
     * get和set系列方法
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Tenement{" +
                "name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    /**
     * 以名字为检索条件
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tenement tenement = (Tenement) o;
        return Objects.equals(name, tenement.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
