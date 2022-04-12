package poo;

import java.util.HashMap;

public class Email {
    private HashMap<String, String> dados;

    public Email(){
        this.dados = new HashMap<>();
    }

    public boolean add(String rotulo, String email){
        if(! this.dados.containsKey(rotulo)){
            String parametro = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";

            if(email.matches(parametro)){
                this.dados.put(rotulo, email);
                return true;
            } else
                return false;
        } else
            return false;
    }

    public boolean remove(String rotulo){
        if(this.dados.containsKey(rotulo)){
            this.dados.remove(rotulo);
            return true;
        } else
            return false;
    }

    public boolean update(String rotulo, String email){
        if(this.dados.containsKey(rotulo)){
            String parametro = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";

            if(email.matches(parametro)){
                this.dados.replace(rotulo, email);
                return true;
            } else
                return false;
        } else
            return false;
    }

    public String toString(){
        return this.dados.toString();
    }
}
