package com.projeto.eventos.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.projeto.eventos.model.Eventos;
import com.projeto.eventos.service.EventosService;

@RestController
@RequestMapping("/eventos")
public class EventosController {

    @Autowired
    private EventosService eventosService;

    @PostMapping("/inserir")
    public ResponseEntity<Eventos> inserirEvento(@RequestBody Eventos evento) {
        eventosService.inserir(evento);
        return ResponseEntity.ok(evento);
    }

    @PutMapping("/atualizar/{nomeEvento}")
    public ResponseEntity<String> atualizarEvento(
            @PathVariable String nomeEvento,
            @RequestParam String novoNome,
            @RequestParam String novaLocalizacao) {
        eventosService.atualizar(nomeEvento, novoNome, novaLocalizacao);
        return ResponseEntity.ok("Evento atualizado com sucesso.");
    }

    @GetMapping("/listar")
    public ResponseEntity<Iterable<Eventos>> listarEventos() {
        Iterable<Eventos> eventos = eventosService.listarEventos();
        return ResponseEntity.ok(eventos);
    }

    @DeleteMapping("/remover/{idEvento}")
    public ResponseEntity<String> removerEvento(@PathVariable Long idEvento) {
        eventosService.remover(idEvento);
        return ResponseEntity.ok("Evento removido com sucesso.");
    }

    @GetMapping("/buscarPorNome/{nomeEvento}")
    public ResponseEntity<Eventos> buscarPorNome(@PathVariable String nomeEvento) {
        Eventos evento = eventosService.buscarPorNome(nomeEvento);
        return ResponseEntity.ok(evento);
    }

    @GetMapping("/buscarPorData")
    public ResponseEntity<Iterable<Eventos>> buscarPorData(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dataFim) {
        Iterable<Eventos> eventosEntre = eventosService.buscarData(dataInicio, dataFim);
        return ResponseEntity.ok(eventosEntre);
    }

    @GetMapping("/buscarPorLocalizacao/{localizacao}")
    public ResponseEntity<Iterable<Eventos>> buscarPorLocalizacao(@PathVariable String localizacao) {
        Iterable<Eventos> eventosLocal = eventosService.buscarPorLocalizacao(localizacao);
        return ResponseEntity.ok(eventosLocal);
    }

    @PostMapping("/adicionarParticipante/{nomeEvento}")
    public ResponseEntity<String> adicionarParticipante(
            @PathVariable String nomeEvento,
            @RequestBody Eventos nomeParticipante) {
        eventosService.adicionarParticipante(nomeEvento, nomeParticipante);
        return ResponseEntity.ok("Participante adicionado com sucesso.");
    }
}
