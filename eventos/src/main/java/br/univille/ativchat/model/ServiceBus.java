package br.univille.ativchat.model;

import br.univille.ativchat.view.Form;
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

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.List;

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
    private List<Mensagem> mensagem;

    public ServiceBus(){
        this.mensagem = new ArrayList<>();
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
                Form.addChat(context.getMessage().getBody().toString());
                System.out.println("Mensagem recebida: " + context.getMessage().getBody().toString());
                context.complete();
            })
            .processError(context -> {
                System.out.println("Erro: " + context.getException().getMessage());
            })
            .buildProcessorClient();
            processorClient.start();
    }

    public void send(Mensagem mensagem) throws InterruptedException {
        senderClient.sendMessage(new ServiceBusMessage(mensagem.getNome() + ": " +mensagem.getTexto()));
    }

    public void evict(){
        this.mensagem.stream().forEach(mensagem1 -> this.mensagem.remove(mensagem1));
    }
}
