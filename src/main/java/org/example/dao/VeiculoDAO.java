package org.example.dao;

import org.example.domain.Carro;
import org.example.domain.Veiculo;
import org.springframework.data.relational.core.sql.SQL;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface VeiculoDAO {


    Veiculo cadastrarCarro(Veiculo veiculo);

    Carro cadastrarCarro(Carro carro) throws SQLException;
   /* Veiculo buscarPorId(Long id);


    List<Veiculo> listarTodos();

    Veiculo atualizar(Veiculo veiculo);

    boolean excluir(Long id);

    List<Veiculo> consultar(String tipo, String modelo, String cor, Integer anoFabricacao);*/

}