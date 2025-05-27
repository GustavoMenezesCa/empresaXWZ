package org.example.service;

import org.example.dao.VeiculoDAO;
import org.example.domain.Carro;
import org.example.domain.Moto;
import org.example.web.dto.CarroCadastroForm;
import org.example.web.dto.MotoCadastroForm;
import org.springframework.stereotype.Service;

@Service
public class MotoService {

    private final VeiculoDAO veiculoDAO;

    public MotoService(VeiculoDAO veiculoDAO){
        this.veiculoDAO = veiculoDAO;
    }

    /*public Moto cadastraMoto(MotoCadastroForm motoCadastroForm){
        if (motoCadastroForm.modelo() == null || motoCadastroForm.fabricante() == null || motoCadastroForm.ano() == null ||
                motoCadastroForm.preco() == null || motoCadastroForm.cilindradas() == null) {
            throw new IllegalArgumentException("Todos os campos são obrigatórios para cadastro de um carro.");
        }
        Moto moto = Moto.fromDto(motoCadastroForm);
        try {
            veiculoDAO.salvar(moto);
            return moto;
        } catch (RuntimeException e) {
            throw new RuntimeException("Erro ao salvar o carro no banco de dados.", e);
        }
    }*/

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
