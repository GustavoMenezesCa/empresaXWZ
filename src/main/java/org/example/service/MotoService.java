package org.example.service;

import org.example.dao.MotoDAO;
import org.example.dao.VeiculoDAO;
import org.example.domain.Carro;
import org.example.domain.Moto;
import org.example.web.dto.CarroCadastroForm;
import org.example.web.dto.MotoCadastroForm;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class MotoService {

    private final MotoDAO motoDAO;

    public MotoService(MotoDAO motoDAO){
        this.motoDAO = motoDAO;
    }

    public Moto cadastraMoto(MotoCadastroForm motoCadastroForm){
        System.out.println(motoCadastroForm);
        if (motoCadastroForm.modelo() == null || motoCadastroForm.fabricante() == null || motoCadastroForm.ano() == null ||
                motoCadastroForm.preco() == null || motoCadastroForm.cilindradas() == null) {
            throw new IllegalArgumentException("Todos os campos são obrigatórios para cadastro de uma moto.");
        }
        Moto moto = Moto.fromDto(motoCadastroForm);
        try {
            motoDAO.cadastrarMoto(moto);
            return moto;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar a moto no banco de dados.", e);
        }
    }

    /*public void excluirMoto(Moto moto) {

        try {
            Moto moto = veiculoDAO.excluir(moto);
            return moto;
        } catch (RuntimeException e) {
            throw new RuntimeException("Erro ao excluir carro no banco de dados.", e);
        }
    }


    public Moto atualizarMoto(Moto moto, MotoCadastroForm motoCadastroForm){
        moto.atualizaDados(motoCadastroForm);

        return veiculoDAO.salvar(moto);
    }*/
}
