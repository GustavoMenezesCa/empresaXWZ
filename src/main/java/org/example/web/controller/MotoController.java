package org.example.web.controller;

import org.example.domain.Carro;
import org.example.domain.Moto;
import org.example.domain.Veiculo;
import org.example.service.CarroService;
import org.example.service.MotoService;
import org.example.service.VeiculoService;
import org.example.web.dto.CarroCadastroForm;
import org.example.web.dto.MotoCadastroForm;
import org.example.web.dto.VeiculoResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/moto")
public class MotoController {

    private final MotoService motoService;

    private final VeiculoService veiculoService;

    public MotoController(MotoService motoService, VeiculoService veiculoService){
        this.motoService = motoService;
        this.veiculoService = veiculoService;
    }


    @PostMapping("/cadastro")
    public ResponseEntity<Object> cadastroMoto(@RequestBody MotoCadastroForm motoCadastroForm){

        Moto moto = motoService.cadastraMoto(motoCadastroForm);

        return ResponseEntity.status(HttpStatus.OK).body(moto);
    }



   /* @GetMapping("/consultar")
    public ResponseEntity<List<VeiculoResponse>> consultarVeiculos(
            @RequestParam(required = false) String tipo,
            @RequestParam(required = false) String modelo,
            @RequestParam(required = false) String fabricante,
            @RequestParam(required = false) Integer ano,
            ) {

        List<VeiculoResponse> veiculos = veiculoService.consultarVeiculos(tipo, modelo, cor, ano);
        return veiculos.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(veiculos);
    }*/


    /*@GetMapping("/consultar/todos")
    public ResponseEntity<List<Veiculo>> listarVeiculos(){
        List<Veiculo> listaVeiculos = veiculoService.();
        return ResponseEntity.status(HttpStatus.OK).body(listaVeiculos);
    }*/

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Object> excluirVeiculo(@PathVariable(value = "id") Long id){
        veiculoService.excluirVeiculo(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /*@PutMapping("/atualizarVeiculo/{id}")
    public ResponseEntity<Moto> atualizarVeiculo(@PathVariable(value = "id") Long id,
                                                 @RequestBody MotoCadastroForm motoCadastroForm){

        Moto moto = motoService.findBy(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Carro não encontrado.")
                );
        Moto motoSalva = motoService.atualizarMoto(moto, motoCadastroForm);
        return ResponseEntity.status(HttpStatus.OK).build(motoSalva);
    }*/


}
