package br.univille.microkernel_kernel.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.univille.microkernel_interface.service.DefaultService;

@RestController
@RequestMapping("/")
public class HomeControllerApi {

    @Autowired
    private Map<String,DefaultService> defaultService;

    @GetMapping("")
    public ResponseEntity<String> buscar(){
        String serviceList = null;

        if(defaultService != null){
            serviceList = Arrays.toString(defaultService.keySet().toArray());

            for(DefaultService serv : defaultService.values()){
                serv.doWork(null);

            }

            System.out.println(defaultService.keySet());
        }

        return ResponseEntity.ok("Microkernel running");
    }

}
