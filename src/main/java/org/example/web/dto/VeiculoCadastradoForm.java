package org.example.web.dto;

import org.example.domain.Carro;

public record VeiculoCadastradoForm(String tipoVeiculo,
                                    String modelo,
                                    String fabricante,
                                    int ano,
                                    Double preco,
                                    Integer quantidadePortas,
                                    String tipoCombustivel,
                                    Integer cilindradas) {


    public boolean isCarro(){
        return "CARRO".equalsIgnoreCase(tipoVeiculo);
    }

    public boolean isMoto(){
        return "MOTO".equalsIgnoreCase(tipoVeiculo);
    }
}
