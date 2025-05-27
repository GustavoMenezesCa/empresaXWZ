package org.example.dao.impl;

import org.example.dao.VeiculoDAO;
import org.example.domain.Carro;
import org.example.domain.Moto;
import org.example.domain.TipoCombustivel;
import org.example.domain.Veiculo;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class VeiculoDAOImpl implements VeiculoDAO {

    private final Connection connection;

    public VeiculoDAOImpl() {
        this.connection = connection;
    }

    @Override
    public Veiculo salvar(Veiculo veiculo) {
        try {
            String sqlVeiculo = "INSERT INTO veiculos (modelo, fabricante, ano_fabricacao, preco, tipo_veiculo) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sqlVeiculo, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, veiculo.getModelo());
            stmt.setString(2, veiculo.getFabricante());
            stmt.setInt(3, veiculo.getAno());
            stmt.setDouble(4, veiculo.getPreco());

            if (veiculo instanceof Carro) {
                stmt.setString(5, "CARRO");
            } else if (veiculo instanceof Moto) {
                stmt.setString(5, "MOTO");
            } else {
                throw new RuntimeException("Tipo de veículo desconhecido");
            }

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                Long id = rs.getInt(1);
                veiculo.setId(id);

                if (veiculo instanceof Carro carro) {
                    String sqlCarro = "INSERT INTO carros (id_veiculo, quantidade_portas, tipo_combustivel) VALUES (?, ?, ?)";
                    PreparedStatement stmtCarro = connection.prepareStatement(sqlCarro);
                    stmtCarro.setInt(1, id);
                    stmtCarro.setInt(2, carro.getQuantPortas());
                    stmtCarro.setString(3, carro.getTipCombustivel().name());
                    stmtCarro.executeUpdate();
                } else if (veiculo instanceof Moto moto) {
                    String sqlMoto = "INSERT INTO motos (id_veiculo, cilindradas) VALUES (?, ?)";
                    PreparedStatement stmtMoto = connection.prepareStatement(sqlMoto);
                    stmtMoto.setInt(1, id);
                    stmtMoto.setInt(2, moto.getCilindradas());
                    stmtMoto.executeUpdate();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar veículo", e);
        }

        return veiculo;
    }

    @Override
    public Veiculo buscarPorId(Long id) {
        return null;
    }

    @Override
    public Optional<Veiculo> buscarPorId(int id) {
        String sql = """
                SELECT v.*, c.quantidade_portas, c.tipo_combustivel, m.cilindradas
                FROM veiculos v
                LEFT JOIN carros c ON v.id = c.id_veiculo
                LEFT JOIN motos m ON v.id = m.id_veiculo
                WHERE v.id = ?
                """;

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String tipo = rs.getString("tipo_veiculo");

                if ("CARRO".equalsIgnoreCase(tipo)) {
                    Carro carro = new Carro();
                    carro.setId(rs.getInt("id"));
                    carro.setModelo(rs.getString("modelo"));
                    carro.setFabricante(rs.getString("fabricante"));
                    carro.setAno(rs.getInt("ano_fabricacao"));
                    carro.setPreco(rs.getDouble("preco"));
                    carro.setQuantPortas(rs.getInt("quantidade_portas"));
                    carro.setTipCombustivel(TipoCombustivel.valueOf(rs.getString("tipo_combustivel")));
                    return Optional.of(carro);
                } else if ("MOTO".equalsIgnoreCase(tipo)) {
                    Moto moto = new Moto();
                    moto.setId(rs.getInt("id"));
                    moto.setModelo(rs.getString("modelo"));
                    moto.setFabricante(rs.getString("fabricante"));
                    moto.setAno(rs.getInt("ano_fabricacao"));
                    moto.setPreco(rs.getDouble("preco"));
                    moto.setCilindradas(rs.getInt("cilindradas"));
                    return Optional.of(moto);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar veículo", e);
        }

        return Optional.empty();
    }

    @Override
    public List<Veiculo> listarTodos() {
        List<Veiculo> lista = new ArrayList<>();

        String sql = """
                SELECT v.*, c.quantidade_portas, c.tipo_combustivel, m.cilindradas
                FROM veiculos v
                LEFT JOIN carros c ON v.id = c.id_veiculo
                LEFT JOIN motos m ON v.id = m.id_veiculo
                """;

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String tipo = rs.getString("tipo_veiculo");

                if ("CARRO".equalsIgnoreCase(tipo)) {
                    Carro carro = new Carro();
                    carro.setId(rs.getInt("id"));
                    carro.setModelo(rs.getString("modelo"));
                    carro.setFabricante(rs.getString("fabricante"));
                    carro.setAno(rs.getInt("ano_fabricacao"));
                    carro.setPreco(rs.getDouble("preco"));
                    carro.setQuantPortas(rs.getInt("quantidade_portas"));
                    carro.setTipCombustivel(TipoCombustivel.valueOf(rs.getString("tipo_combustivel")));
                    lista.add(carro);
                } else if ("MOTO".equalsIgnoreCase(tipo)) {
                    Moto moto = new Moto();
                    moto.setId(rs.getInt("id"));
                    moto.setModelo(rs.getString("modelo"));
                    moto.setFabricante(rs.getString("fabricante"));
                    moto.setAno(rs.getInt("ano_fabricacao"));
                    moto.setPreco(rs.getDouble("preco"));
                    moto.setCilindradas(rs.getInt("cilindradas"));
                    lista.add(moto);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar veículos", e);
        }

        return lista;
    }

    @Override
    public Veiculo atualizar(Veiculo veiculo) {
        try {
            String sql = """
                    UPDATE veiculos
                    SET modelo = ?, fabricante = ?, ano_fabricacao = ?, preco = ?, tipo_veiculo = ?
                    WHERE id = ?
                    """;
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, veiculo.getModelo());
            stmt.setString(2, veiculo.getFabricante());
            stmt.setInt(3, veiculo.getAno());
            stmt.setDouble(4, veiculo.getPreco());

            if (veiculo instanceof Carro) {
                stmt.setString(5, "CARRO");
            } else if (veiculo instanceof Moto) {
                stmt.setString(5, "MOTO");
            }

            stmt.setInt(6, veiculo.getId());
            stmt.executeUpdate();

            if (veiculo instanceof Carro carro) {
                String sqlUpdateCarro = """
                        UPDATE carros
                        SET quantidade_portas = ?, tipo_combustivel = ?
                        WHERE id_veiculo = ?
                        """;
                PreparedStatement stmtCarro = connection.prepareStatement(sqlUpdateCarro);
                stmtCarro.setInt(1, carro.getQuantPortas());
                stmtCarro.setString(2, carro.getTipCombustivel().name());
                stmtCarro.setInt(3, carro.getId());
                stmtCarro.executeUpdate();
            } else if (veiculo instanceof Moto moto) {
                String sqlUpdateMoto = """
                        UPDATE motos
                        SET cilindradas = ?
                        WHERE id_veiculo = ?
                        """;
                PreparedStatement stmtMoto = connection.prepareStatement(sqlUpdateMoto);
                stmtMoto.setInt(1, moto.getCilindradas());
                stmtMoto.setInt(2, moto.getId());
                stmtMoto.executeUpdate();
            }

            return veiculo;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar veículo", e);
        }
    }

    @Override
    public boolean excluir(Long id) {
        return false;
    }

    @Override
    public boolean excluir(int id) {
        String sql = "DELETE FROM veiculos WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir veículo", e);
        }
    }

    @Override
    public List<Veiculo> consultar(String tipo, String modelo, String cor, Integer anoFabricacao) {
        // Ajuste caso cor esteja presente na tabela veiculos
        StringBuilder sql = new StringBuilder("""
            SELECT v.*, c.quantidade_portas, c.tipo_combustivel, m.cilindradas
            FROM veiculos v
            LEFT JOIN carros c ON v.id = c.id_veiculo
            LEFT JOIN motos m ON v.id = m.id_veiculo
            WHERE 1=1
        """);

        List<Object> parametros = new ArrayList<>();

        if (tipo != null && !tipo.isBlank()) {
            sql.append(" AND v.tipo_veiculo = ?");
            parametros.add(tipo.toUpperCase());
        }

        if (modelo != null && !modelo.isBlank()) {
            sql.append(" AND LOWER(v.modelo) LIKE ?");
            parametros.add("%" + modelo.toLowerCase() + "%");
        }

        if (anoFabricacao != null) {
            sql.append(" AND v.ano_fabricacao = ?");
            parametros.add(anoFabricacao);
        }

        List<Veiculo> resultado = new ArrayList<>();

        try {
            PreparedStatement stmt = connection.prepareStatement(sql.toString());
            for (int i = 0; i < parametros.size(); i++) {
                stmt.setObject(i + 1, parametros.get(i));
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String tipoVeiculo = rs.getString("tipo_veiculo");
                if ("CARRO".equalsIgnoreCase(tipoVeiculo)) {
                    Carro carro = new Carro();
                    carro.setId(rs.getInt("id"));
                    carro.setModelo(rs.getString("modelo"));
                    carro.setFabricante(rs.getString("fabricante"));
                    carro.setAno(rs.getInt("ano_fabricacao"));
                    carro.setPreco(rs.getDouble("preco"));
                    carro.setQuantPortas(rs.getInt("quantidade_portas"));
                    carro.setTipCombustivel(TipoCombustivel.valueOf(rs.getString("tipo_combustivel")));
                    resultado.add(carro);
                } else if ("MOTO".equalsIgnoreCase(tipoVeiculo)) {
                    Moto moto = new Moto();
                    moto.setId(rs.getInt("id"));
                    moto.setModelo(rs.getString("modelo"));
                    moto.setFabricante(rs.getString("fabricante"));
                    moto.setAno(rs.getInt("ano_fabricacao"));
                    moto.setPreco(rs.getDouble("preco"));
                    moto.setCilindradas(rs.getInt("cilindradas"));
                    resultado.add(moto);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar veículos", e);
        }

        return resultado;
    }
}