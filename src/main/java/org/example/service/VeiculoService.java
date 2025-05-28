package org.example.service;

import org.example.dao.VeiculoDAO;
import org.example.domain.Carro;
import org.example.domain.Veiculo;
import org.example.web.dto.VeiculoResponse;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class VeiculoService {

    private final VeiculoDAO veiculoDAO;

    public VeiculoService(VeiculoDAO veiculoDAO){
        this.veiculoDAO = veiculoDAO;
    }


    public void excluirVeiculo(Long id) {

        try {
            veiculoDAO.excluirVeiculo(id);
        }
        catch (SQLException e) {
        throw new RuntimeException("Erro ao excluir carro no banco de dados.", e);
        }
    }

    public List<Veiculo> listarVeiculosCadastrados(){
        return veiculoDAO.listarTodos();
    }

    public List<Veiculo> consultarVeiculos(String tipo, String modelo, String cor, Integer ano) {
        return veiculoDAO.consultarVeiculoFiltrado(tipo, modelo, cor, ano);
    }


   /* public List<VeiculoResponse> consultarVeiculos(String tipo, String modelo, String cor, Integer ano) {
        try {
            List<Veiculo> veiculosConsultados = veiculoDAO.consultarComFiltros(tipo, modelo, cor, ano);
            return VeiculoResponse.fromEntity(veiculosConsultados);

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar veículos.", e);
        }
    }*/

}


