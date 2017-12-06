package e.bruno.listview;

/**
 * Created by bruno on 04/12/2017.
 */

public class Char {
    private String nome;
    private int iniciativa;
    private int modificador;

    public int getModificador() {
        return modificador;
    }

    public void setModificador(int modificador) {
        this.modificador = modificador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIniciativa() {
        return iniciativa;
    }

    public void setIniciativa(int iniciativa) {
        this.iniciativa = iniciativa;
    }

    public String iniciativaToString() {
        return String.valueOf(iniciativa);
    }
    public String modificadorToString() {
        return String.valueOf(modificador);
    }

    public Char(String nome, int iniciativa, int modificador) {
        this.nome = nome;
        this.iniciativa = iniciativa;
        this.modificador = modificador;
    }

}
