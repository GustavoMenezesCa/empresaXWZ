package org.example.web.dto;

public record CarroCadastradoForm(String modelo,
                                  String fabricante,
                                  int ano,
                                  Double preco,
                                  int quantidadePortas,
                                  String tipoCombustivel) {
}
