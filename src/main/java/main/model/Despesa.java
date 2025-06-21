package main.model;

import java.time.LocalDate;

public class Despesa extends Lancamento {
    public Despesa(String descricao, double valor, LocalDate data) {
    super(descricao, valor, data, false, "Despesa");
}

}
