package org.example.service;

import org.example.dao.VeiculoDAO;
import org.example.domain.Carro;
import org.example.domain.Moto;
import org.example.domain.Veiculo;
import org.example.web.dto.VeiculoCadastradoForm;

public class VeiculoService {

    private final VeiculoDAO veiculoDAO;

    public VeiculoService(VeiculoDAO veiculoDAO){
        this.veiculoDAO = veiculoDAO;
    }

    public Object cadastraVeiculo(VeiculoCadastradoForm veiculoCadastradoForm){

        if (veiculoCadastradoForm.isCarro()){
            Carro carro = Carro.fromDto(veiculoCadastradoForm);
            veiculoDAO.salvar(carro);
            return carro;
        }
        if (veiculoCadastradoForm.isMoto()){
            Moto moto = Moto.fromDto(veiculoCadastradoForm);
            veiculoDAO.salvar(moto);
            return moto;
        }
        return null;
    }

}
