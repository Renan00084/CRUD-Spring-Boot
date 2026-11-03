package com.renan.teste_spring.config;

import org.javalite.activejdbc.Base;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializerUser implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        // 1. Abre a ligação no início da aplicação
        System.out.println("Ligar à base de dados SQLite...");
        try {
            Base.open("org.sqlite.JDBC", "jdbc:sqlite:Usuarios", "", "");

            // 2. Garante que a tabela existe
            String sqlCreate = "CREATE TABLE IF NOT EXISTS users (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name VARCHAR(100), " +
                    "arroba VARCHAR(100), " +
                    "cpf VARCHAR(20))";

            Base.exec(sqlCreate);
            // A PARTIR DAQUI A LIGAÇÃO ESTÁ ABERTA E A TABELA EXISTE

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Base.close();
            System.out.println("Ligação encerrada.");
        }
    }
}
