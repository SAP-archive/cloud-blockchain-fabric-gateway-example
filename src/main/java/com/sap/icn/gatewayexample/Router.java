/*
 * Copyright 2020 SAP All Rights Reserved.
 */

package com.sap.icn.gatewayexample;

import org.hyperledger.fabric.gateway.Network;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sap.cloud.sdk.services.blockchain.fabric.service.FabricService;

@SpringBootApplication
@RestController
public class Router {

    private ChaincodeInvoker chaincodeInvoker;

    public static void main(String[] args) {
        SpringApplication.run(Router.class, args);
    }

    @PostMapping(value = "/{cc}/query", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    byte[] query(@PathVariable("cc") String chaincode, @org.springframework.web.bind.annotation.RequestBody RequestBody parameters) {
        return chaincodeInvoker.query(chaincode, parameters.getFunction(), parameters.getArguments());
    }

    @PostMapping(value = "/{cc}/invoke", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    byte[] invoke(@PathVariable("cc") String chaincode, @org.springframework.web.bind.annotation.RequestBody RequestBody parameters) {
        return chaincodeInvoker.invoke(chaincode, parameters.getFunction(), parameters.getArguments());
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initialize() {
        final Network network = FabricService.provideNetwork();
        chaincodeInvoker = new ChaincodeInvoker(network);
    }
}
