package com.amd.dto;

import lombok.Data;

import java.security.PrivateKey;
import java.security.PublicKey;

@Data
public class RSAKeys {
    PrivateKey privateKey;
    PublicKey publicKey;

    public RSAKeys(PrivateKey privateKey, PublicKey publicKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }
}