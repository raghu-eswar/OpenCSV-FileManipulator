package com.opencsv;

import com.opencsv.bean.CsvBindByName;

import java.util.Objects;

public class MyUser {
    @CsvBindByName(column = "NAME")
    private String name;
    @CsvBindByName(column = "MAIL")
    private String mail;
    @CsvBindByName(column = "PHONE")
    private String phone;

    public String getName() {
        return name;
    }

    public MyUser setName(String name) {
        this.name = name;
        return this;
    }

    public String getMail() {
        return mail;
    }

    public MyUser setMail(String mail) {
        this.mail = mail;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public MyUser setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    @Override
    public String toString() {
        return "MyUser{" +
                "name='" + name + '\'' +
                ", mail='" + mail + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyUser myUser = (MyUser) o;
        return Objects.equals(name, myUser.name) &&
                Objects.equals(mail, myUser.mail) &&
                Objects.equals(phone, myUser.phone);
    }
}
