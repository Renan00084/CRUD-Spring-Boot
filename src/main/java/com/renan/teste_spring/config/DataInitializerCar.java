package com.renan.teste_spring.config;

import org.javalite.activejdbc.Base;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializerCar implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Ligando à base de dados SQLite...");

        try {
            Base.open("org.sqlite.JDBC",
                    "jdbc:sqlite:Usuarios", "", "");

            String sqlCreate = "CREATE TABLE IF NOT EXISTS cars (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "marca VARCHAR(100), " +
                    "modelo VARCHAR(100), " +
                    "ano INTEGER)";

            Base.exec(sqlCreate);

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            Base.close();
            System.out.println("Ligação encerrada.");
        }
    }
}
