package org.example.web.controller;

import org.example.domain.Carro;
import org.example.domain.Veiculo;
import org.example.service.CarroService;
import org.example.service.VeiculoService;
import org.example.web.dto.CarroCadastroForm;

import org.example.web.dto.CarroUpdateForm;
import org.example.web.dto.VeiculoResponse;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/carro")
public class CarroController {

    private final CarroService carroService;

    private final VeiculoService veiculoService;

    public CarroController(CarroService carroService, VeiculoService veiculoService){
        this.carroService = carroService;
        this.veiculoService = veiculoService;
    }

    //cadatrar veiculo
    @PostMapping("/cadastro")
    public ResponseEntity<Object> cadastraCarro(@RequestBody CarroCadastroForm carroCadastroForm){

        Carro carro = carroService.cadastraCarro(carroCadastroForm);

        return ResponseEntity.status(HttpStatus.OK).body(carro);
    }



    @GetMapping("/consultar")
    public ResponseEntity<List<Carro>> listarCarro(@RequestBody CarroUpdateForm carroUpdateForm) throws SQLException {

        List<Carro> carro = carroService.listaCarroFiltrados(carroUpdateForm.modelo(), carroUpdateForm.cor(), carroUpdateForm.ano());
        return ResponseEntity.status(HttpStatus.OK).body(carro);
    }



    @GetMapping("/consultar/todos")
    public ResponseEntity<List<Veiculo>> listarVeiculos(){
        List<Veiculo> listaVeiculos = veiculoService.listarVeiculosCadastrados();
        return ResponseEntity.status(HttpStatus.OK).body(listaVeiculos);
    }

   @DeleteMapping("/excluir/{Id}")
    public ResponseEntity<Object> excluirVeiculo(@PathVariable(value = "Id") Long id){

        veiculoService.excluirVeiculo(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /*@PutMapping("/atualizarVeiculo")
    public ResponseEntity<Carro> atualizarVeiculo(@RequestBody CarroCadastroForm carroCadastroForm){

        Carro carro = carroService.findBy(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Carro não encontrado.")
                );
        Carro carroSalvo = carroService.atualizarCarro(carro, carroCadastroForm);
        return ResponseEntity.status(HttpStatus.OK).build(carroSalvo);
    }*/
}


