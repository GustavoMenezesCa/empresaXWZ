package org.example.dao.impl;

import jakarta.annotation.sql.DataSourceDefinition;
import org.example.dao.VeiculoDAO;
import org.example.domain.Carro;
import org.example.domain.Veiculo;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Configuration
public class VeiculoDAOImpl implements VeiculoDAO {
    private static Connection conn = null;

    private Connection getConnection() throws SQLException {

        String conexao = "jdbc:postgresql://localhost:5432/postgres";
        String usuario = "postgres";
        String senha = "12345";

        return DriverManager.getConnection(conexao, usuario, senha);
    }

    public void excluirVeiculo(Long id) throws SQLException {
        String sql = "DELETE FROM VEICULO WHERE id = ?;";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            int affectedRows = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new SQLException("Erro ao excluir veículo com ID " + id + ": " + e.getMessage(), e);
        }
    }





















   /* @Override
    public Veiculo salvar(Veiculo veiculo) {
        return null;
    }

    @Override
    public Veiculo buscarPorId(Long id) {
        return null;
    }

    @Override
    public List<Veiculo> listarTodos() {
        return null;
    }

    @Override
    public Veiculo atualizar(Veiculo veiculo) {
        return null;
    }

    @Override
    public boolean excluir(Long id) {
        return false;
    }*/

    /*@Override
    public List<Veiculo> consultar(String tipo, String modelo, String cor, Integer anoFabricacao) {
        return null;
    }try*/
}
