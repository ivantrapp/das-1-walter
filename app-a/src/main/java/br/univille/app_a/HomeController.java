package br.univille.app_a;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.dapr.client.DaprClient;
import io.dapr.client.DaprClientBuilder;
import io.dapr.client.domain.HttpExtension;

@RestController
@RequestMapping("/api")
public class HomeController {

    //  ******** APPLICAÇÃO AAAAAAAAA  ************

    @GetMapping()
    public ResponseEntity index() {
        
        try(DaprClient daprClient = new DaprClientBuilder().build()){
            System.out.println("Hello from b");
            daprClient.invokeMethod("app-b", "/api", null, HttpExtension.GET).block();            
        } catch (Exception e) {
            // TODO: handle exception
        }

        return ResponseEntity.ok().body("Hello from App A");
    }

    @PostMapping("/startaasync")
    public ResponseEntity startAAsync(){
        System.out.println("App A started");
        
        try(DaprClient daprClient = new DaprClientBuilder().build()){
            var message = "Bombástica fabrica de mensagem";
            System.out.println("Rodou AA");
            daprClient.publishEvent("pubsub-dapr", "topicdapr", message).block();
        } catch(Exception e){
            e.printStackTrace();
        }

        return ResponseEntity.ok().body("App A started");
    }
}