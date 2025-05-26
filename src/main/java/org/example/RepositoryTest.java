// Pacote: org.example
package org.example;

import org.example.config.DatabaseSetup; // IMPORTAR
import org.example.dao.VeiculoDAO;

import org.example.dao.impl.VeiculoDAOImpl;

import org.example.domain.Carro;

import java.sql.SQLException; // IMPORTAR


public class RepositoryTest {

    public static void main(String[] args) {
        // **PASSO 1: Inicializar/Resetar o Banco de Dados**
        try {
            System.out.println(">>> PREPARANDO BANCO DE DADOS PARA TESTES...");
            DatabaseSetup.initializeDatabase(); // Chama o método de setup
            System.out.println(">>> BANCO DE DADOS PRONTO PARA TESTES.\n");
        } catch (SQLException e) {
            System.err.println("!!! FALHA CRÍTICA AO PREPARAR O BANCO DE DADOS PARA TESTES. ABORTANDO. !!!");
            e.printStackTrace();
            return; // Aborta os testes se o setup do banco falhar
        }

        // **PASSO 2: Proceder com os Testes do Repositório**
        VeiculoDAO repository = new VeiculoDAOImpl();
        System.out.println("--- INICIANDO TESTES DO REPOSITÓRIO ---");

        // ... (resto dos seus testes: salvar carro, salvar moto, listar, etc.) ...
        // Exemplo:
        Carro carroSalvo = null;
        System.out.println("\n[TESTE 1] SALVAR CARRO");
        try {
            Carro carro1 = new Carro();
            carro1.setModelo("Kwid");
            carro1.setFabricante("Renault");
            // ... resto dos dados do carro1 ...
            carro1.setQuantPortas(4);
            carro1.setTipCombustivel("FLEX");
            carroSalvo = (Carro) repository.salvar(carro1);
            System.out.println("  Carro salvo: " + carroSalvo);
            // ...
        } catch (Exception e) {
            System.err.println("  ERRO ao salvar carro: " + e.getMessage());
            e.printStackTrace();
        }

        // ... (outros testes) ...

        System.out.println("\n--- TESTES DO REPOSITÓRIO FINALIZADOS ---");
    }
}