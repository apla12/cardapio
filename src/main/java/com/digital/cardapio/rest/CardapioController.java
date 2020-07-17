package com.digital.cardapio.rest;

import com.digital.cardapio.model.entity.Cardapio;
import com.digital.cardapio.model.repository.CardapioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/cardapios")
public class CardapioController {

    private final CardapioRepository repository;

    @Autowired
    public CardapioController(CardapioRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Cardapio> obterTodos(){
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cardapio salvar(@RequestBody @Valid Cardapio cardapio){

        return repository.save(cardapio);
    }

    @GetMapping("{id}")
    public Cardapio acharPorId(@PathVariable Integer id ){
        return repository
                .findById(id)
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado") );
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar( @PathVariable Integer id ){
        repository
            .findById(id)
            .map( cliente -> {
                repository.delete(cliente);
                return Void.TYPE;
            })
            .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado") );
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar( @PathVariable Integer id,
                           @RequestBody @Valid Cardapio cardapioAtualizado) {
        repository
                .findById(id)
                .map( cliente -> {
                    cliente.setNome(cardapioAtualizado.getNome());
                    return repository.save(cliente);
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado") );
    }
}
