package poo;

import java.util.ArrayList;
import java.util.Collections;

public class Agenda {
    private ArrayList<Pessoa> contatos;

    public Agenda(){
        this.contatos = new ArrayList<>();
    }

    public void addPessoa(Pessoa p){
        this.contatos.add(p);
    }

    public boolean removePessoa(String nome, String sobrenome){
        for(Pessoa elemento: this.contatos){
            if(elemento.toString().contains(nome + " " + sobrenome)){
                this.contatos.remove(elemento);
                return true;
            }
        }

        return false;
    }

    public boolean addTelefone(String rotulo, String numero, int pIndex){
        try {
            return this.contatos.get(pIndex).addTelefone(rotulo, numero);
        } catch (Exception e){
            return false;
        }
    }

    public boolean addEmail(String rotulo, String email, int pIndex){
        try{
            return this.contatos.get(pIndex).addEmail(rotulo, email);
        } catch (Exception e){
            return false;
        }
    }

    public boolean updateNumero(String rotulo, String numero, int pIndex){
        try {
            return this.contatos.get(pIndex).updateTelefone(rotulo, numero);
        } catch (Exception e){
            return false;
        }
    }

    public boolean upateEmail(String rotulo, String email, int pIndex){
        try {
            return this.contatos.get(pIndex).updateEmail(rotulo, email);
        } catch (Exception e){
            return false;
        }
    }

    public boolean removeTelefone(String rotulo, int pIndex){
        try {
            return this.contatos.get(pIndex).removeTelefone(rotulo);
        } catch (Exception e){
            return false;
        }
    }

    public boolean removeEmail(String rotulo, int pIndex){
        try {
            return this.contatos.get(pIndex).removeEmail(rotulo);
        } catch (Exception e){
            return false;
        }
    }

    public void compara(){
        Collections.sort(this.contatos);
    }

    public String toString(){
        return "\n" + contatos.toString() + "\n";
    }
}
