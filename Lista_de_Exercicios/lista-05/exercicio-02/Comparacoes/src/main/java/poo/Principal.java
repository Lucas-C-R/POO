package poo;

import java.util.ArrayList;
import java.util.Collections;

public class Principal {
    public static void main(String[] args) {
        ArrayList<Pessoa> pessoas = new ArrayList<>();

        pessoas.add(new Pessoa("Joao","Silva" , "1990-01-01"));
        pessoas.add(new Pessoa("Ana", "Paula" , "1989-01-01"));
        pessoas.add(new Pessoa ("Joao", "Santos", "1991-01-01"));
        pessoas.add(new Pessoa ("Joao", "Silva" , "1980-01-01"));

        System.out.println(pessoas);

        Collections.sort(pessoas);

        System.out.println(pessoas);

        Agenda agenda = new Agenda();

        agenda.addPessoa(new Pessoa("Joao","Silva" , "1990-01-01"));
        agenda.addTelefone("Celular", "5548999063247", 0);
        agenda.addEmail("Pessoal", "joao.silva@gmail.com", 0);

        agenda.addPessoa(new Pessoa("Ana", "Paula" , "1989-01-01"));
        agenda.addTelefone("Celular", "5547983279002", 1);
        agenda.addEmail("Pessoal", "ana.paula@gmail.com", 1);

        agenda.addPessoa(new Pessoa ("Joao", "Santos", "1991-01-01"));
        agenda.addTelefone("Celular", "5548998038297", 2);
        agenda.addEmail("Pessoal", "joao.santos@hmail.com", 2);

        agenda.addPessoa(new Pessoa ("Joao", "Silva" , "1980-01-01"));
        agenda.addTelefone("Celular", "479990366034", 3);
        agenda.addEmail("Trabalho", "joao.silva@hotmail.com.br", 3);

        System.out.println(agenda);

        agenda.compara();

        System.out.println(agenda);
    }
}
