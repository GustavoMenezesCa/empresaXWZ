package org.example.dao.impl;

import jakarta.annotation.sql.DataSourceDefinition;
import org.example.dao.VeiculoDAO;
import org.example.domain.Carro;
import org.example.domain.Veiculo;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;
import java.util.Properties;
@Configuration
public class VeiculoDAOImpl implements VeiculoDAO {
    private static Connection conn = null;

    private Connection getConnection() throws SQLException{

        String conexao = "jdbc:postgresql://localhost:5432/postgres";
        String usuario = "postgres";
        String senha = "123";

        return DriverManager.getConnection(conexao, usuario, senha);
    }

    @Override
    public Veiculo cadastrarCarro(Veiculo veiculo) {
        return null;
    }

    public Carro cadastrarCarro(Carro carro) throws SQLException{

        String sqlVeiculo = "INSERT INTO VEICULO(modelo, fabricante, ano, preco, tipo_veiculo) VALUES (?,?,?,?, 'CARRO');";
        String sqlCarro = "INSERT INTO CARRO (id_veiculo, quantidade_portas, tipo_combustivel) VALUES (?,?,?);";

        Connection conn = null;
        PreparedStatement pstmtVeiculo = null;
        PreparedStatement pstmtCarro = null;
        ResultSet generatedKeys = null;

        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            pstmtVeiculo = conn.prepareStatement(sqlVeiculo, Statement.RETURN_GENERATED_KEYS);
            pstmtVeiculo.setString(1, carro.getModelo());
            pstmtVeiculo.setString(2, carro.getFabricante());
            pstmtVeiculo.setInt(3, carro.getAno());
            pstmtVeiculo.setDouble(4, carro.getPreco());

            int affectedRowsVeiculo = pstmtVeiculo.executeUpdate();

            /*if (affectedRowsVeiculo == 0) {
                throw new SQLException("Falha ao inserir em VEICULO, nenhuma linha afetada.");
            }*/

            generatedKeys = pstmtVeiculo.getGeneratedKeys();
            if (generatedKeys.next()) {
                carro.setId(generatedKeys.getLong(1)); // Define o ID no objeto carro
            } else {
                conn.rollback(); // Desfaz a inserção em VEICULO se não conseguiu ID
                throw new SQLException("Falha ao obter o ID do veículo gerado.");
            }
            pstmtCarro = conn.prepareStatement(sqlCarro);
            pstmtCarro.setLong(1, carro.getId()); // Usa o ID obtido acima
            pstmtCarro.setInt(2, carro.getQuantPortas());
            pstmtCarro.setString(3, carro.getTipCombustivel().toString());

            int affectedRowsCarro = pstmtCarro.executeUpdate();

            if (affectedRowsCarro == 0) {
                conn.rollback(); // Desfaz tudo se falhar ao inserir em CARRO
                throw new SQLException("Falha ao inserir em CARRO, nenhuma linha afetada.");
            }

            conn.commit();
            return carro;
        }
        catch (SQLException e){
            if (conn != null) {
                try {
                    System.err.println("Transação está sendo revertida para o cadastro do carro.");
                    conn.rollback();
                } catch (SQLException excep) {
                    System.err.println("Erro ao tentar reverter a transação: " + excep.getMessage());
                }
        }
            throw new SQLException("Erro ao cadastrar carro: " + e.getMessage(), e);
    } finally {
            if (generatedKeys != null) try { generatedKeys.close(); } catch (SQLException e) { /* log ou ignore */ }
            if (pstmtVeiculo != null) try { pstmtVeiculo.close(); } catch (SQLException e) { /* log ou ignore */ }
            if (pstmtCarro != null) try { pstmtCarro.close(); } catch (SQLException e) { /* log ou ignore */ }
            if (conn != null) {
                try {
                    conn.setAutoCommit(true); // Restaura o modo de autocommit padrão
                    conn.close();
                } catch (SQLException e) { /* log ou ignore */ }
            }
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
