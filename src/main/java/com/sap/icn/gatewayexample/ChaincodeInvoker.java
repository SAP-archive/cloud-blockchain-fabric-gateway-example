/*
 * Copyright 2020 SAP All Rights Reserved.
 */

package com.sap.icn.gatewayexample;

import org.hyperledger.fabric.gateway.ContractException;
import org.hyperledger.fabric.gateway.Network;

import java.util.concurrent.TimeoutException;

public class ChaincodeInvoker {

    private Network channel;

    public ChaincodeInvoker(Network channel) {
        this.channel = channel;
    }

    public byte[] invoke(String chaincode, String function, String... args) {
        try {
            return channel.getContract(chaincode).submitTransaction(function, args);
        } catch (InterruptedException | TimeoutException | ContractException e) {
            return e.getMessage().getBytes();
        }
    }

    public byte[] query(String chaincode, String function, String... args) {
        try {
            return channel.getContract(chaincode).evaluateTransaction(function, args);
        } catch (ContractException e) {
            return e.getMessage().getBytes();
        }
    }
}
