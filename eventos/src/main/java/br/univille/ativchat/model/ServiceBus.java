package br.univille.ativchat.model;

import com.azure.core.amqp.AmqpTransportType;
import com.azure.identity.DefaultAzureCredential;
import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusMessage;
import com.azure.messaging.servicebus.ServiceBusProcessorClient;
import com.azure.messaging.servicebus.ServiceBusSenderClient;
import com.azure.messaging.servicebus.administration.ServiceBusAdministrationClient;
import com.azure.messaging.servicebus.administration.ServiceBusAdministrationClientBuilder;
import com.azure.messaging.servicebus.models.ServiceBusReceiveMode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
@Builder
public class ServiceBus {
    
    private String topicName;
    private String subscriptionName;
    private String fqdns;
    private DefaultAzureCredential credential;
    private ServiceBusSenderClient senderClient;
    private ServiceBusProcessorClient processorClient;
    private ServiceBusAdministrationClient adminClient;

    public ServiceBus(){
        this.topicName = "topic-chat";
        this.subscriptionName = "subscription-ivan";
        this.fqdns = "sb-das12025-test-brazilsouth.servicebus.windows.net";
        this.credential = new DefaultAzureCredentialBuilder().build();

        this.senderClient = new ServiceBusClientBuilder()
        .fullyQualifiedNamespace("sb-das12025-test-brazilsouth.servicebus.windows.net")
        .credential(credential)
        .transportType(AmqpTransportType.AMQP_WEB_SOCKETS)
        .sender()
        .topicName(topicName)
        .buildClient();

        this.adminClient = new ServiceBusAdministrationClientBuilder()
            .credential(fqdns, credential)
            .buildClient();
        
        try {
            this.adminClient.createSubscription(topicName, subscriptionName);
        } catch (Exception e) {
            // TODO: handle exception
        }

        this.processorClient = new ServiceBusClientBuilder()
            .fullyQualifiedNamespace(fqdns)
            .credential(credential)
            .transportType(AmqpTransportType.AMQP_WEB_SOCKETS)
            .processor()
            .topicName(topicName)
            .subscriptionName(subscriptionName)
            .receiveMode(ServiceBusReceiveMode.PEEK_LOCK)
            .processMessage(context -> {
                System.out.println("Mensagem recebida: " + context.getMessage().getBody().toString());
                context.complete();
            })
            .processError(context -> {
                System.out.println("Erro: " + context.getException().getMessage());
            })
            .buildProcessorClient();        
    }

    public void send(Mensagem mensagem){
        senderClient.sendMessage(new ServiceBusMessage(mensagem.getNome() + ": " +mensagem.getTexto()));
    }
}
