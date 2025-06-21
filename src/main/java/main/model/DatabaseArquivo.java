package main.model;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DatabaseArquivo implements Database{
    private final String arquivo = "lancamentos.csv";

    @Override
    public void salvarLancamentos(List<Lancamento> lancamentos) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(arquivo))) {
            for (Lancamento l : lancamentos) {
                pw.println(l.getDescricao() + ";" + l.getValor() + ";" + l.getData() + ";" + l.isReceita() + ";" + l.getCategoria());
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar os lançamentos" + e.getMessage());
        }
    }

    @Override
    public List<Lancamento> carregarLancamentos() {
        List<Lancamento> lancamentos = new ArrayList<>();
        File file = new File(arquivo);
        if (!file.exists()) {
            return lancamentos;
        }
            try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
                String linha;
                while ((linha = br.readLine()) != null) {
                    String [] partes = linha.split(";");
                    if (partes.length == 5) {
                        String descricao = partes[0];
                        double valor = Double.parseDouble (partes[1]);
                        LocalDate data = LocalDate.parse (partes[2]);
                        boolean tipo = Boolean.parseBoolean(partes[3]);
                        String categoria = partes[4];
                        lancamentos.add(new Lancamento(descricao, valor, data, tipo, categoria));
                    } else if (partes.length == 4) {
                        String descricao = partes[0];
                        double valor = Double.parseDouble(partes[1]);
                        LocalDate data = LocalDate.parse(partes[2]);
                        boolean tipo = Boolean.parseBoolean(partes[3]);
                        String categoria = tipo ? "Receita" : "Despesa";
                        lancamentos.add(new Lancamento(descricao, valor, data, tipo, categoria));

                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return lancamentos;
        
        }
    }   