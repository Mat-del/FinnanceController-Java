package main.model;

import java.time.LocalDate;

public class Receita extends Lancamento {
       public Receita(String descricao, double valor, LocalDate data, String categoria) {
    super(descricao, valor, data, true, categoria);
}

}
