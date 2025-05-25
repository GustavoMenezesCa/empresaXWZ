package org.example.domain;

public class Moto extends Veiculo {
    private int cilindradas;

    public Moto (){}
    public Moto(int cilindradas) {
        this.cilindradas = cilindradas;
    }

    public Moto(int id, String modelo, String fabricante, int ano, double preco, int cilindradas) {
        super(id, modelo, fabricante, ano, preco);
        this.cilindradas = cilindradas;
    }

    public int getCilindradas() {
        return cilindradas;
    }

    public void setCilindradas(int cilindradas) {
        this.cilindradas = cilindradas;
    }
}
