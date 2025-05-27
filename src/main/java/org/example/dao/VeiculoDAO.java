package org.example.dao;

import org.example.domain.Veiculo;

import java.util.List;
import java.util.Optional;

public interface VeiculoDAO {


    Veiculo salvar(Veiculo veiculo);


    Veiculo buscarPorId(Long id);


    List<Veiculo> listarTodos();

    Veiculo atualizar(Veiculo veiculo);

    boolean excluir(Long id);

    List<Veiculo> consultar(String tipo, String modelo, String cor, Integer anoFabricacao);

}