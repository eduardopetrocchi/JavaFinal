package com.projeto.eventos.service;

import java.util.Date;

import com.projeto.eventos.model.Eventos;

public interface EventosService {
    void inserir (Eventos nomeEvento);

    void atualizar (String nomeEvento, String novoNome, String novaLocalizacao);

    Iterable<Eventos> listarEventos();

    void remover(Long idEvento);

    Eventos buscarPorNome(String nomeEvento);

    Iterable<Eventos> buscarData(Date dataInicio, Date dataFim);

    Iterable<Eventos> buscarPorLocalizacao(String localizacao);

    void adicionarParticipante(String nomeEvento, Eventos nomeParticipante);
    
}



