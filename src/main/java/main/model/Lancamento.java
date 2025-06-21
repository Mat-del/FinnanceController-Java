package main.model;
import java.time.LocalDate;


public class Lancamento {
    private String descricao;
    private double valor;
    private LocalDate data;
    private boolean receita;
    private String categoria;

    public Lancamento(String descricao, double valor, LocalDate data, boolean receita, String categoria) {
        this.descricao = descricao;
        this.valor = valor;
        this.data = LocalDate.now();
        this.receita = receita;
        this.categoria = categoria;
    }

    public Lancamento(String descricao, double valor, boolean receita) {
        this.descricao = descricao;
        this.valor = valor;
        this.data = LocalDate.now();
        this.receita = receita;
        this.categoria = "categoria";
    }


    public String getDescricao() {
        return descricao;
    }

    public double getValor() {
        return valor;
    }

    public LocalDate getData() {
        return data;
    }

    public boolean isReceita() {
        return receita;
    }

    public String getCategoria() {
        return categoria;
    }

}
       




