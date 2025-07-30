package com.example.projeto;

public class OrcamentoMensal {
    private int id;
    private int usuarioId;
    private double limite;
    private double gastoAtual;
    private int mes;
    private int ano;

    public OrcamentoMensal(int usuarioId, double limite, double gastoAtual, int mes, int ano) {
        this.usuarioId = usuarioId;
        this.limite = limite;
        this.gastoAtual = gastoAtual;
        this.mes = mes;
        this.ano = ano;
    }

    public OrcamentoMensal() {}

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

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public double getGastoAtual() {
        return gastoAtual;
    }

    public void setGastoAtual(double gastoAtual) {
        this.gastoAtual = gastoAtual;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        if (mes < 1 || mes > 12) {
            throw new IllegalArgumentException("Mês inválido. Deve estar entre 1 e 12.");
        }
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        if (ano < 0) {
            throw new IllegalArgumentException("Ano inválido. Deve ser maior que zero.");
        }
        this.ano = ano;
    }
}
