package org.example.web.controller;

import org.example.domain.Veiculo;
import org.example.service.VeiculoService;
import org.example.web.dto.VeiculoCadastradoForm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    private final VeiculoService veiculoService;

    public VeiculoController(VeiculoService veiculoService){
        this.veiculoService = veiculoService;
    }

    @PostMapping("/cadastro")
    public ResponseEntity<Object> cadastrarVeiculo(VeiculoCadastradoForm veiculoCadastradoForm){
        //implementar no service

        Veiculo veiculo = (Veiculo) veiculoService.cadastraVeiculo(veiculoCadastradoForm);

        return ResponseEntity.status(HttpStatus.OK).body(veiculo);
    }

    @DeleteMapping("/excluir/{Id}")
    public ResponseEntity<Object> excluirVeiculo(@PathVariable(value = "Id") Long id){

        veiculoService.excluirVeiculo(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
