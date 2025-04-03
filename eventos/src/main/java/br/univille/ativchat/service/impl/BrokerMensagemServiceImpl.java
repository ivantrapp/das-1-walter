package br.univille.ativchat.service.impl;

import java.security.Provider.Service;
import java.util.List;

import org.checkerframework.checker.units.qual.s;

import com.azure.core.amqp.AmqpTransportType;
import com.azure.identity.DefaultAzureCredential;
import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusSenderClient;
import com.google.inject.Inject;

import br.univille.ativchat.model.Mensagem;
import br.univille.ativchat.model.ServiceBus;
import br.univille.ativchat.service.BrokerMensagemService;

public class BrokerMensagemServiceImpl implements BrokerMensagemService {
    String topicName = "topic-chat";
    String serviceBusCanal = "sb-das12025-test-brazilsouth.servicebus.windows.net";
    String subscription = "subscription-" +  System.getenv("USERNAME");

    private ServiceBus serviceBus;

    @Inject
    public BrokerMensagemServiceImpl (ServiceBus serviceBus){
        this.serviceBus = serviceBus;
    }

    @Override
    public void enviarMensagem(Mensagem mensagem) {
        System.out.println(mensagem.getNome() + " : " + mensagem.getTexto());
        serviceBus.send(mensagem);
    }

    @Override
    public void buscarMensagens(List<Mensagem> mensagens) {
        serviceBus.getProcessorClient().start();
        System.out.println("Aguardando mensagens...");
        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            serviceBus.getProcessorClient().close();
        }
    }
    
}
