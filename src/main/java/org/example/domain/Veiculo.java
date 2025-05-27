package org.example.domain;

import org.example.web.dto.VeiculoResponse;

public class Veiculo {
    private Long id;
    private String modelo;
    private String fabricante;
    private int ano;
    private double preco;

    public Veiculo(){}
    public Veiculo(String modelo, String fabricante, int ano, Double preco) {
        this.modelo = modelo;
        this.fabricante = fabricante;
        this.ano = ano;
        this.preco = preco;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public VeiculoResponse fromEntity(Veiculo veiculo){
        return new VeiculoResponse(modelo, fabricante, ano,
                preco);
    }
}
