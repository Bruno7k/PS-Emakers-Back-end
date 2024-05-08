package com.emakers.api.data.entity;

public enum PessoaTipo {

    ADMIN("admin"),
    USUARIO("usuario");

    private String tipo;

    PessoaTipo(String tipo){
        this.tipo = tipo;
    }

    public String getTipo(){
        return tipo;
    }
}
