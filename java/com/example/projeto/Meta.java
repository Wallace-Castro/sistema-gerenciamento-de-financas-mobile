package com.example.projeto;

public class Meta {
    private int id;
    private int usuarioId;
    private double valorMeta;
    private double progresso;

    public Meta(int usuarioId, double valorMeta, double progresso) {
        this.usuarioId = usuarioId;
        this.valorMeta = valorMeta;
        this.progresso = progresso;
    }

    public Meta() {}

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

    public double getValorMeta() {
        return valorMeta;
    }

    public void setValorMeta(double valorMeta) {
        this.valorMeta = valorMeta;
    }

    public double getProgresso() {
        return progresso;
    }

    public void setProgresso(double progresso) {
        this.progresso = Math.min(progresso, 100);
    }
}
