package org.example.dao;

import org.example.domain.Veiculo;

import java.util.List;
import java.util.Optional;

public interface VeiculoDAO {

    /**
     * Salva um novo veículo (Carro ou Moto) no banco de dados.
     * Lida com a inserção na tabela 'veiculos' e na tabela específica ('carros' ou 'motos').
     * @param veiculo O veículo a ser salvo.
     * @return O veículo salvo, idealmente com o ID preenchido após a inserção.
     */
    Veiculo salvar(Veiculo veiculo);

    /**
     * Busca um veículo pelo seu ID.
     * Retorna o tipo específico (Carro ou Moto) se encontrado.
     * @param id O ID do veículo.
     * @return Um Optional contendo o Veiculo (Carro ou Moto) ou Optional.empty() se não encontrado.
     */
    Optional<Veiculo> buscarPorId(int id);

    /**
     * Lista todos os veículos cadastrados.
     * @return Uma lista contendo todos os Veiculos (Carro ou Moto).
     */
    List<Veiculo> listarTodos();

    /**
     * Atualiza um veículo existente no banco de dados.
     * Lida com a atualização na tabela 'veiculos' e na tabela específica.
     * @param veiculo O veículo com os dados atualizados.
     * @return O veículo atualizado.
     */
    Veiculo atualizar(Veiculo veiculo);

    /**
     * Exclui um veículo do banco de dados pelo seu ID.
     * Se ON DELETE CASCADE estiver configurado, a exclusão da tabela 'veiculos'
     * também removerá da tabela específica.
     * @param id O ID do veículo a ser excluído.
     * @return true se a exclusão foi bem-sucedida, false caso contrário.
     */
    boolean excluir(int id);

    /**
     * Consulta veículos com base em filtros.
     * @param tipo "CARRO", "MOTO" ou null/vazio para todos os tipos.
     * @param modelo Parte do nome do modelo ou null/vazio.
     * @param cor Cor do veículo ou null/vazio.
     * @param anoFabricacao Ano de fabricação ou null.
     * @return Uma lista de veículos que correspondem aos filtros.
     */
    List<Veiculo> consultar(String tipo, String modelo, String cor, Integer anoFabricacao);

}