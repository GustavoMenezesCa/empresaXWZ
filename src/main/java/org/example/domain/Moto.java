package org.example.domain;

import org.example.web.dto.CarroCadastroForm;
import org.example.web.dto.MotoCadastroForm;

public class Moto extends Veiculo {
    private Integer cilindradas;

    public Moto (){}
    public Moto(int cilindradas) {
        this.cilindradas = cilindradas;
    }

    public Moto(String modelo, String fabricante, int ano, double preco, Integer cilindradas) {
        super(modelo, fabricante, ano, preco);
        this.cilindradas = cilindradas;
    }

    public Integer getCilindradas() {
        return cilindradas;
    }

    public void setCilindradas(Integer cilindradas) {
        this.cilindradas = cilindradas;
    }

    public static Moto fromDto(MotoCadastroForm motoCadastroForm){
        return new Moto(motoCadastroForm.modelo(), motoCadastroForm.fabricante(),
                motoCadastroForm.ano(), motoCadastroForm.preco(),
                motoCadastroForm.cilindradas());
    }
}
