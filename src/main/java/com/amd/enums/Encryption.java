package com.amd.enums;

public enum Encryption {

    RSA("RSA"), SHA("SHA-256"), AES("AES/CBC/PKCS5Padding");

    private String name;

    Encryption(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}