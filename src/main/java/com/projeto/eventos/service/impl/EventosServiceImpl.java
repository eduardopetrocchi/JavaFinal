package com.projeto.eventos.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.eventos.model.Eventos;
import com.projeto.eventos.model.EventosRepository;
import com.projeto.eventos.service.EventosService;

@Service
public class EventosServiceImpl implements EventosService {

    @Autowired
    private EventosRepository eventosRepository;

    private List<Eventos> eventosLista = new ArrayList<>();

    private List<Eventos> eventosEntre = new ArrayList<>();

    private List<Eventos> eventosLocal = new ArrayList<>();

    private List<Eventos> participante = new ArrayList<>();

    @Override
    public void inserir(Eventos nomeEvento) {
        eventosLista.add(nomeEvento);
    }

    @Override
    public void atualizar(String nomeEvento, String novoNome, String novaLocalizacao) {
        for (Eventos evento : eventosLista) {
            if(evento.getNomeEvento().equals(nomeEvento)){
                evento.setNomeEvento(novoNome);
                evento.setLocalizacao(novaLocalizacao);
                System.out.println("O evento " + nomeEvento + "foi alterado para" + novoNome + "em" + novaLocalizacao);
            }
        }
    }

    @Override
    public Iterable<Eventos> listarEventos() {
        return eventosLista;
    }

    @Override
    public void remover(Long idEvento) {
        eventosRepository.deleteById(idEvento);
    }

    @Override
    public Eventos buscarPorNome(String nomeEvento) {
        for (Eventos evento : eventosLista){
            if (evento.getNomeEvento().equals(nomeEvento)){
                return evento;
            }
        }
        throw new NoSuchElementException("Nenhum evento encontrado com o nome: " + nomeEvento);
    }

    @Override
    public Iterable<Eventos> buscarData(Date dataInicio, Date dataFim) {
        for (Eventos evento : eventosLista){
            Date dataEvento = evento.getDataInicio();
            if (dataEvento != null && (dataEvento.after(dataInicio) || dataEvento.equals(dataInicio))
            && (dataEvento.before(dataFim) || dataEvento.equals(dataFim)))
            {
                eventosEntre.add(evento);
            }}
            return eventosEntre;
    }

    @Override
    public Iterable<Eventos> buscarPorLocalizacao(String localizacao) {
            for (Eventos evento : eventosLista){
                if (evento.getLocalizacao().equals(localizacao)){
                    eventosLocal.add(evento);
                }
            }
            return eventosLocal;
    }

    @Override
    public void adicionarParticipante(String nomeEvento, Eventos nomeParticipante) {
        participante.add(nomeParticipante);
    }
    
}
