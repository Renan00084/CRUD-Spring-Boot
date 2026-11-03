package com.renan.teste_spring.model;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

@Table("users")
public class User extends Model {

    public String getName() {
        return getString("name");
    }

    public void setName(String name) {
        set("name", name);
    }

    public String getArroba() {
        return getString("arroba");
    }

    public void setArroba(String arroba) {
        set("arroba", arroba);
    }

    public String getCpf() {
        return getString("cpf");
    }

    public void setCpf(String cpf) {
        set("cpf", cpf);
    }
}