package poo;

import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.util.HashMap;

public class Telefone {
    private HashMap<String, String> dados;

    public Telefone(){
        this.dados = new HashMap<>();
    }

    public boolean add(String rotulo, String numero){
        if(! this.dados.containsKey(rotulo)){
            try{
                MaskFormatter mascara = new MaskFormatter("+## (##) #####-####");
                mascara.setValueContainsLiteralCharacters(false);
                mascara.setPlaceholderCharacter('_');

                this.dados.put(rotulo, mascara.valueToString(numero));
                return true;
            } catch (ParseException e) {
                return false;
            }
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

    public boolean update(String rotulo, String numero){
        if(this.dados.containsKey(rotulo)){
            try{
                MaskFormatter mascara = new MaskFormatter("+## (##) #####-####");
                mascara.setValueContainsLiteralCharacters(false);
                mascara.setPlaceholderCharacter('_');

                this.dados.replace(rotulo, mascara.valueToString(numero));
                return true;
            } catch (ParseException e) {
                return false;
            }
        } else
            return false;
    }

    public String toString(){
        return this.dados.toString();
    }
}
