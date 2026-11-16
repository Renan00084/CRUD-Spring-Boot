package com.renan.teste_spring.model;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

@Table("cars")
public class Car extends Model {

    public String getMarca() {
        return getString("marca");
    }

    public void setMarca(String marca) {
        set("marca", marca);
    }

    public String getModelo() {
        return getString("modelo");
    }

    public void setModelo(String modelo) {
        set("modelo", modelo);
    }

    public int getAno() {
        return getInteger("ano");
    }

    public void setAno(int ano) {
        set("ano", ano);
    }
}
