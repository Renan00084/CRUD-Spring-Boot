package com.renan.teste_spring.model;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

@Table("games")
public class Game extends Model {

    public String getNome() {
        return getString("nome");
    }

    public void setNome(String nome) {
        set("nome", nome);
    }

    public String getGenero() {
        return getString("genero");
    }

    public void setGenero(String genero) {
        set("genero", genero);
    }

    public String getPlataforma() {
        return getString("plataforma");
    }

    public void setPlataforma(String plataforma) {
        set("plataforma", plataforma);
    }
}
