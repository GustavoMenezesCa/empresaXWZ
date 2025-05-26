package org.example.domain;

import org.example.web.dto.VeiculoCadastradoForm;

public class Carro extends Veiculo {
    private int quantPortas;
    private TipoCombustivel tipCombustivel;

    public Carro (){}

    public Carro(int quantPortas, TipoCombustivel tipCombustivel) {
        this.quantPortas = quantPortas;
        this.tipCombustivel = tipCombustivel;
    }

    public Carro( String modelo, String fabricante, int ano, double preco, int quantPortas, TipoCombustivel tipCombustivel) {
        super(modelo, fabricante, ano, preco);
        this.quantPortas = quantPortas;
        this.tipCombustivel = tipCombustivel;
    }

    public Carro(String modelo, String fabricante, Double preco, int quantidadePortas, TipoCombustivel tipoCombustivel) {
    }

    public int getQuantPortas() {
        return quantPortas;
    }

    public void setQuantPortas(int quantPortas) {
        this.quantPortas = quantPortas;
    }

    public TipoCombustivel getTipCombustivel() {
        return tipCombustivel;
    }

    public void setTipCombustivel(TipoCombustivel tipCombustivel) {
        this.tipCombustivel = tipCombustivel;
    }


    public static Carro fromDto(VeiculoCadastradoForm veiculoCadastradoForm){
        return new Carro(veiculoCadastradoForm.modelo(), veiculoCadastradoForm.fabricante(),
                veiculoCadastradoForm.ano(), veiculoCadastradoForm.preco(),
                veiculoCadastradoForm.quantidadePortas(), TipoCombustivel.fromString(veiculoCadastradoForm.tipoCombustivel()));
    }

}
