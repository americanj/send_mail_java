package model;

public class Contato {
    private String email;
    private String nome;


    public Contato(String email, String nome){
        this.email = email;
        this.nome = nome;
    }

    public Contato(String email){
        this.email = email;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

   /* @Override
    public String toString() {
        return email + " - " + nome;
    }*/
}
