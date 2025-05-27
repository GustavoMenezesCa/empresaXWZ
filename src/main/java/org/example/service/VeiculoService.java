package org.example.service;

import org.example.dao.VeiculoDAO;
import org.example.domain.Veiculo;
import org.example.web.dto.VeiculoResponse;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class VeiculoService {

    private final VeiculoDAO veiculoDAO;

    public VeiculoService(VeiculoDAO veiculoDAO){
        this.veiculoDAO = veiculoDAO;
    }

    public List<Veiculo> listarVeiculosCadastrados(){
        return veiculoDAO.listarTodos();
    }

    /*public List<VeiculoResponse> listarTodosVeiculosCadastrados() {
        try {
            List<Veiculo> listaVeiculos = veiculoDAO.listarTodos();
            // Converter para DTOs de resposta
            return listaVeiculos.stream()
                    .map(this::listarTodosVeiculosCadastrados) // Reutilizar seu método de conversão
                    .collect(Collectors.toList());
        } catch (SQLException e) {
            // Logar o erro
            throw new RuntimeException("Erro ao listar todos os veículos.", e);
        }
    }*/

    public List<VeiculoResponse> consultarVeiculos(String tipo, String modelo, String cor, Integer ano) {
        try {
            List<Veiculo> veiculosConsultados = veiculoDAO.consultarComFiltros(tipo, modelo, cor, ano);
            return VeiculoResponse.fromEntity(veiculosConsultados);

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar veículos.", e);
        }
    }

}


