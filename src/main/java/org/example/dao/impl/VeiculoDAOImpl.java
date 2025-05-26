// Pacote: org.example.repository
package org.example.dao.impl;

import org.example.config.ConnectionFactory;
import org.example.dao.VeiculoDAO;

import org.example.domain.Carro;
import org.example.domain.Moto;
import org.example.domain.Veiculo;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VeiculoDAOImpl implements VeiculoDAO {

    // Constantes para nomes de colunas
    private static final String VEICULO_ID = "id";
    private static final String VEICULO_MODELO = "modelo";
    private static final String VEICULO_FABRICANTE = "fabricante";
    private static final String VEICULO_ANO = "ano_fabricacao";
    private static final String VEICULO_PRECO = "preco";
    private static final String VEICULO_TIPO = "tipo_veiculo";

    private static final String CARRO_ID_VEICULO = "id_veiculo";
    private static final String CARRO_QTD_PORTAS = "quantidade_portas";
    private static final String CARRO_TIPO_COMBUSTIVEL = "tipo_combustivel";

    private static final String MOTO_ID_VEICULO = "id_veiculo";
    private static final String MOTO_CILINDRADAS = "cilindradas";

    @Override
    public Veiculo salvar(Veiculo veiculo) {
        String sqlVeiculo = "INSERT INTO veiculos (modelo, fabricante, ano_fabricacao, preco, tipo_veiculo) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmtVeiculo = conn.prepareStatement(sqlVeiculo, Statement.RETURN_GENERATED_KEYS)) {

            pstmtVeiculo.setString(1, veiculo.getModelo());
            pstmtVeiculo.setString(2, veiculo.getFabricante());
            pstmtVeiculo.setInt(3, veiculo.getAno());
            pstmtVeiculo.setDouble(4, veiculo.getPreco());

            if (veiculo instanceof Carro) {
                pstmtVeiculo.setString(5, "CARRO");
            } else if (veiculo instanceof Moto) {
                pstmtVeiculo.setString(5, "MOTO");
            } else {
                throw new IllegalArgumentException("Tipo de veículo desconhecido para salvar.");
            }
            pstmtVeiculo.executeUpdate();

            try (ResultSet generatedKeys = pstmtVeiculo.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    veiculo.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Falha ao salvar veículo, nenhum ID obtido.");
                }
            }

            if (veiculo instanceof Carro) {
                Carro carro = (Carro) veiculo;
                String sqlCarro = "INSERT INTO carros (id_veiculo, quantidade_portas, tipo_combustivel) VALUES (?, ?, ?)";
                try (PreparedStatement pstmtCarro = conn.prepareStatement(sqlCarro)) {
                    pstmtCarro.setInt(1, carro.getId());
                    pstmtCarro.setInt(2, carro.getQuantPortas());
                    pstmtCarro.setString(3, carro.getTipCombustivel());
                    pstmtCarro.executeUpdate();
                }
            } else if (veiculo instanceof Moto) {
                Moto moto = (Moto) veiculo;
                String sqlMoto = "INSERT INTO motos (id_veiculo, cilindradas) VALUES (?, ?)";
                try (PreparedStatement pstmtMoto = conn.prepareStatement(sqlMoto)) {
                    pstmtMoto.setInt(1, moto.getId());
                    pstmtMoto.setInt(2, moto.getCilindradas());
                    pstmtMoto.executeUpdate();
                }
            }
            return veiculo;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao salvar veículo: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<Veiculo> buscarPorId(int id) {
        String sql = "SELECT v.*, c.quantidade_portas, c.tipo_combustivel, m.cilindradas " +
                "FROM veiculos v " +
                "LEFT JOIN carros c ON v.id = c.id_veiculo AND v.tipo_veiculo = 'CARRO' " +
                "LEFT JOIN motos m ON v.id = m.id_veiculo AND v.tipo_veiculo = 'MOTO' " +
                "WHERE v.id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRowToVeiculo(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar veículo por ID: " + e.getMessage(), e);
        }
        return Optional.empty();
    }

    @Override
    public List<Veiculo> listarTodos() {
        List<Veiculo> veiculos = new ArrayList<>();
        String sql = "SELECT v.*, c.quantidade_portas, c.tipo_combustivel, m.cilindradas " +
                "FROM veiculos v " +
                "LEFT JOIN carros c ON v.id = c.id_veiculo AND v.tipo_veiculo = 'CARRO' " +
                "LEFT JOIN motos m ON v.id = m.id_veiculo AND v.tipo_veiculo = 'MOTO' " +
                "ORDER BY v.id";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                veiculos.add(mapRowToVeiculo(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao listar todos os veículos: " + e.getMessage(), e);
        }
        return veiculos;
    }

    @Override
    public Veiculo atualizar(Veiculo veiculo) {
        String sqlVeiculo = "UPDATE veiculos SET modelo = ?, fabricante = ?, ano_fabricacao = ?, preco = ? WHERE id = ?";
        // ASSUMINDO QUE O TIPO DO VEÍCULO (CARRO/MOTO) NÃO MUDA NA ATUALIZAÇÃO

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmtVeiculo = conn.prepareStatement(sqlVeiculo)) {

            pstmtVeiculo.setString(1, veiculo.getModelo());
            pstmtVeiculo.setString(2, veiculo.getFabricante());
            pstmtVeiculo.setInt(3, veiculo.getAno());
            pstmtVeiculo.setDouble(4, veiculo.getPreco());
            pstmtVeiculo.setInt(5, veiculo.getId());
            pstmtVeiculo.executeUpdate();

            if (veiculo instanceof Carro) {
                Carro carro = (Carro) veiculo;
                String sqlCarro = "UPDATE carros SET quantidade_portas = ?, tipo_combustivel = ? WHERE id_veiculo = ?";
                try (PreparedStatement pstmtCarro = conn.prepareStatement(sqlCarro)) {
                    pstmtCarro.setInt(1, carro.getQuantPortas());
                    pstmtCarro.setString(2, carro.getTipCombustivel());
                    pstmtCarro.setInt(3, carro.getId());
                    pstmtCarro.executeUpdate();
                }
            } else if (veiculo instanceof Moto) {
                Moto moto = (Moto) veiculo;
                String sqlMoto = "UPDATE motos SET cilindradas = ? WHERE id_veiculo = ?";
                try (PreparedStatement pstmtMoto = conn.prepareStatement(sqlMoto)) {
                    pstmtMoto.setInt(1, moto.getCilindradas());
                    pstmtMoto.setInt(2, moto.getId());
                    pstmtMoto.executeUpdate();
                }
            }
            return veiculo;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao atualizar veículo: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean excluir(Long id) {
        String sql = "DELETE FROM veiculos WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao excluir veículo: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Veiculo> consultar(String tipo, String modelo, String cor, Integer anoFabricacao) {
        List<Veiculo> veiculos = new ArrayList<>();
        StringBuilder sqlBuilder = new StringBuilder(
                "SELECT v.*, c.quantidade_portas, c.tipo_combustivel, m.cilindradas " +
                        "FROM veiculos v " +
                        "LEFT JOIN carros c ON v.id = c.id_veiculo AND v.tipo_veiculo = 'CARRO' " +
                        "LEFT JOIN motos m ON v.id = m.id_veiculo AND v.tipo_veiculo = 'MOTO' " +
                        "WHERE 1=1"
        );
        List<Object> params = new ArrayList<>();

        if (tipo != null && !tipo.trim().isEmpty()) {
            sqlBuilder.append(" AND v.tipo_veiculo = ?");
            params.add(tipo.toUpperCase());
        }
        if (modelo != null && !modelo.trim().isEmpty()) {
            sqlBuilder.append(" AND v.modelo ILIKE ?");
            params.add("%" + modelo + "%");
        }
        // COLUNA COR - se adicionar, descomente:
        /*
        if (cor != null && !cor.trim().isEmpty()) {
            sqlBuilder.append(" AND v.cor ILIKE ?"); // Assumindo que 'cor' está na tabela 'veiculos'
            params.add("%" + cor + "%");
        }
        */
        if (anoFabricacao != null && anoFabricacao > 0) {
            sqlBuilder.append(" AND v.ano_fabricacao = ?");
            params.add(anoFabricacao);
        }
        sqlBuilder.append(" ORDER BY v.id");

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlBuilder.toString())) {

            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(i + 1, params.get(i));
            }

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    veiculos.add(mapRowToVeiculo(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao consultar veículos: " + e.getMessage(), e);
        }
        return veiculos;
    }

    private Veiculo mapRowToVeiculo(ResultSet rs) throws SQLException {
        String tipoVeiculo = rs.getString(VEICULO_TIPO);
        Veiculo veiculo;

        if ("CARRO".equals(tipoVeiculo)) {
            Carro carro = new Carro();
            carro.setQuantPortas(rs.getInt(CARRO_QTD_PORTAS));
            carro.setTipCombustivel(rs.getString(CARRO_TIPO_COMBUSTIVEL));
            veiculo = carro;
        } else if ("MOTO".equals(tipoVeiculo)) {
            Moto moto = new Moto();
            moto.setCilindradas(rs.getInt(MOTO_CILINDRADAS));
            veiculo = moto;
        } else {
            throw new SQLException("Tipo de veículo desconhecido no banco: '" + tipoVeiculo + "' para o ID: " + rs.getInt(VEICULO_ID));
        }

        veiculo.setId(rs.getInt(VEICULO_ID));
        veiculo.setModelo(rs.getString(VEICULO_MODELO));
        veiculo.setFabricante(rs.getString(VEICULO_FABRICANTE));
        veiculo.setAno(rs.getInt(VEICULO_ANO));
        veiculo.setPreco(rs.getDouble(VEICULO_PRECO));
        // Se adicionar 'cor':
        // if (rs.getMetaData().getColumnIndex("cor") > 0) { // Verifica se a coluna existe
        //    veiculo.setCor(rs.getString("cor"));
        // }

        return veiculo;
    }
}