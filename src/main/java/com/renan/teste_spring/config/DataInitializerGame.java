package com.renan.teste_spring.config;

import org.javalite.activejdbc.Base;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializerGame implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Ligando à base de dados SQLite...");

        try {
            Base.open("org.sqlite.JDBC",
                    "jdbc:sqlite:Usuarios", "", "");

            String sqlCreate = "CREATE TABLE IF NOT EXISTS games (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nome VARCHAR(100), " +
                    "genero VARCHAR(100), " +
                    "plataforma VARCHAR(100))";

            Base.exec(sqlCreate);

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            Base.close();
            System.out.println("Ligação encerrada.");
        }
    }
}
