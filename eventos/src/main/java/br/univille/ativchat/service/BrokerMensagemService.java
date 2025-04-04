package br.univille.ativchat.service;

import java.util.List;

import br.univille.ativchat.model.Mensagem;

public interface BrokerMensagemService {
    void enviarMensagem(Mensagem mensagem);
    List<Mensagem> buscarMensagens();
}
