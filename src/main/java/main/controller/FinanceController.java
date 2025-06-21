package main.controller;

import java.util.List;
import main.model.Database;
import main.model.DatabaseArquivo;
import main.model.Lancamento;


public class FinanceController {
    private List<Lancamento> lancamentos;
    private Database db = new DatabaseArquivo();

    public FinanceController() {
        lancamentos = db.carregarLancamentos();
    }  
    
    public List<Lancamento> getLancamentos() {
        return lancamentos;
    }



   public double getSaldo() {
        double saldo = 0;
        for (Lancamento l: lancamentos) {
            saldo += l.isReceita() ? l.getValor() : -l.getValor();
            
        }
        return  saldo;
    }

    public void adicionarLancamento(Lancamento lancamento) {
        lancamentos.add(lancamento);
        db.salvarLancamentos(lancamentos);
    }

    public void limparLancamentos() {
        lancamentos.clear();
        db.salvarLancamentos(lancamentos);
    }

    public void listarLancamentosConsole() {
        for (Lancamento l : lancamentos) {
            System.out.printf("%s | %s | %.2f | %s\n",
            l.getData(), l.getDescricao(), l.getValor(), l.isReceita() ? "Receita" : "Despesa");
        }
        
    }  
    
    public java.util.List<Lancamento> getComprasDoMes() {
        List<Lancamento> compras = new java.util.ArrayList<>();
        java.time.LocalDate agora = java.time.LocalDate.now();
        for (Lancamento l : lancamentos) {
           if (!l.isReceita() &&
            l.getData().getMonth() == agora.getMonth() &&
            l.getData().getYear() == agora.getYear()) {
                compras.add(l);
            }
            
        }
        return compras;
    }

    }

