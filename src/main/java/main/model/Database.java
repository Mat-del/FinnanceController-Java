package main.model;


import java.util.List;

public interface Database {
    List<Lancamento> carregarLancamentos();
    void salvarLancamentos(List<Lancamento> lancamentos);
}

