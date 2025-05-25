package org.example.web.dto;

public record MotoCadastroForm(String modelo,
                               String fabricante,
                               int ano,
                               Double preco,
                               int cilindradas) {

}
