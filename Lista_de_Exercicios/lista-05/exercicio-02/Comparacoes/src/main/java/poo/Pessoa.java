package poo;

import java.time.LocalDate;

public class Pessoa implements Comparable<Pessoa>{
    private String nome;
    private String sobrenome;
    private LocalDate dataNasc;
    private Telefone telefone;
    private Email emails;

    public Pessoa(String nome, String sobrenome, String dataNasc){
        this.nome = nome;
        this.sobrenome = sobrenome;

        String dia = dataNasc.substring(8, 10);
        String mes = dataNasc.substring(5, 7);
        String ano = dataNasc.substring(0, 4);

        this.dataNasc = LocalDate.of(Integer.parseInt(ano), Integer.parseInt(mes), Integer.parseInt(dia));
        this.telefone = new Telefone();
        this.emails = new Email();
    }

    public boolean addTelefone(String rotulo, String numero){
        return this.telefone.add(rotulo, numero);
    }

    public boolean addEmail(String rotulo, String email){
        return this.emails.add(rotulo, email);
    }

    public boolean removeTelefone(String rotulo){
        return this.telefone.remove(rotulo);
    }

    public boolean removeEmail(String rotulo){
        return this.emails.remove(rotulo);
    }

    public boolean updateTelefone(String rotulo, String numero){
        return this.telefone.update(rotulo, numero);
    }

    public boolean updateEmail(String rotulo, String email){
        return this.emails.update(rotulo, email);
    }

    @Override
    public int compareTo(Pessoa pessoa) {
        for(int j = 0; j < 3; j++){
            byte[] c1;
            byte[] c2;

            switch (j){
                case 0:
                    c1 = this.nome.getBytes();
                    c2 = pessoa.nome.getBytes();
                    break;

                case 1:
                    c1 = this.sobrenome.getBytes();
                    c2 = pessoa.sobrenome.getBytes();
                    break;

                case 2:
                    String d1 = this.dataNasc.toString();
                    String d2 = pessoa.dataNasc.toString();

                    c1 = d1.getBytes();
                    c2 = d2.getBytes();
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + j);
            }

            for(int i = 0; i < this.nome.length(); i++){
                if(c1[i] > c2[i]){
                    return 1;
                } else if(c1[i] < c2[i]){
                    return -1;
                }
            }
        }

        return 0;
    }

    public String toString(){
        return "\n" + "Nome: " + this.nome + " " + this.sobrenome + "\n" +
                "Data de nascimento: " + this.dataNasc + "\n" +
                "Telefone: " + telefone.toString() + "\n" +
                "Emails: " + emails.toString() + "\n";
    }
}
