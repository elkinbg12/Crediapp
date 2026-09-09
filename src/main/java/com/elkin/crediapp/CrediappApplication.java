package com.elkin.crediapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrediappApplication {

    public static void main(String[] args) {

        // Inicializa o contêiner do Spring, injeta dependências e testa a conexão com o banco
        SpringApplication.run(CrediappApplication.class, args);

        System.out.println("\n Crediapp iniciado com Spring boot com sucesso!\n");
    }

}
