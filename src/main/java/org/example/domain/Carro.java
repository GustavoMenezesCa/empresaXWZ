package org.example.domain;

public class Carro extends Veiculo {
    private int quantPortas;
    private String tipCombustivel;

    public Carro (){}
    public Carro(int quantPortas, String tipCombustivel) {
        this.quantPortas = quantPortas;
        this.tipCombustivel = tipCombustivel;
    }

    public Carro(int id, String modelo, String fabricante, int ano, double preco, int quantPortas, String tipCombustivel) {
        super(id, modelo, fabricante, ano, preco);
        this.quantPortas = quantPortas;
        this.tipCombustivel = tipCombustivel;
    }

    public int getQuantPortas() {
        return quantPortas;
    }

    public void setQuantPortas(int quantPortas) {
        this.quantPortas = quantPortas;
    }

    public String getTipCombustivel() {
        return tipCombustivel;
    }

    public void setTipCombustivel(String tipCombustivel) {
        this.tipCombustivel = tipCombustivel;
    }
}
