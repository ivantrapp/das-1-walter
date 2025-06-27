package br.univille.app_b.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.dapr.Topic;
import io.dapr.client.domain.CloudEvent;

@RestController
@RequestMapping("/api")
public class HomeController {

    //  ******** APPLICAÇÃO B  ************

    @GetMapping()
    public ResponseEntity index() {
        return ResponseEntity.ok().body("Hello from App B");
    }

    @Topic(pubsubName="pubsub-dapr", name="topicdapr")
    @PostMapping(path = "/startbasync", consumes = MediaType.ALL_VALUE)
    public ResponseEntity startBAsync(@RequestBody(required= false) CloudEvent<String> evento){

        System.out.println("App B started");

        var id = evento.getId();
        var mensagem = evento.getData();

        System.out.println("Mensagem" + mensagem);
        System.out.println("id" + id);
        return ResponseEntity.ok().body("body");
    }
}