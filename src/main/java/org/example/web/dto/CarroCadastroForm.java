package org.example.web.dto;

import org.example.domain.Carro;

public record CarroCadastroForm(
                                    String modelo,
                                    String fabricante,
                                    Integer ano,
                                    Double preco,
                                    Integer quantidadePortas,
                                    String tipoCombustivel
                                    ) {



}
