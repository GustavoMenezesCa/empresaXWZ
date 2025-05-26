package org.example.domain;

import org.example.web.dto.VeiculoCadastradoForm;

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

    public static Moto fromDto(VeiculoCadastradoForm veiculoCadastradoForm){
        return new Moto(veiculoCadastradoForm.modelo(), veiculoCadastradoForm.fabricante(),
                veiculoCadastradoForm.ano(), veiculoCadastradoForm.preco(),
                veiculoCadastradoForm.cilindradas());
    }
}
