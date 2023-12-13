package br.com.ada.ifome.ifome.controller;

import br.com.ada.ifome.ifome.model.Entregador;
import br.com.ada.ifome.ifome.service.EntregadorService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/entregadores")
public class EntregadorController {

    private final EntregadorService entregadorService;

    @PostMapping
    public ResponseEntity<Entregador> salvar(@RequestBody Entregador entregador) {
        return ResponseEntity.status(201).body(entregadorService.salvar(entregador));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Entregador> remover(@PathVariable Long id) {
        return ResponseEntity.ok(entregadorService.remover(id));
    }
}
