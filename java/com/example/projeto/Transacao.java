package com.example.projeto;

public class Transacao {
    private int id;
    private int usuarioId;
    private double valor;
    private String data;
    private String categoria;

    public Transacao(int usuarioId, double valor, String data, String categoria) {
        this.usuarioId = usuarioId;
        this.valor = valor;
        this.data = data;
        this.categoria = categoria;
    }

    public Transacao() {}

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
