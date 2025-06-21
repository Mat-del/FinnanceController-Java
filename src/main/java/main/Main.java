package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.time.LocalDate;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import com.formdev.flatlaf.FlatLightLaf;
import main.controller.FinanceController;
import main.model.Lancamento;


public class Main {
    public static void main(String[] args) {
        try {
            FlatLightLaf.setup();
        } catch (Exception ex) {
            System.err.println("Falha ao iniciar o FlatLaf");
        }

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Meu Controle Financeiro");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setUndecorated(true);
            frame.setSize(900, 600);
            frame.setLayout(new BorderLayout());

            
            JPanel topPanel = new JPanel(new BorderLayout());
            JLabel titulo = new JLabel("Meu Controle Financeiro");
            titulo.setFont(new Font("Arial", Font.PLAIN, 28));
            topPanel.add(titulo, BorderLayout.WEST);

            JButton btnFechar = new JButton("X");
            btnFechar.setForeground(Color.RED);
            btnFechar.setFont(new Font("Arial", Font.BOLD, 24));
            btnFechar.setFocusPainted(false);
            btnFechar.addActionListener(e -> frame.dispose());
            topPanel.add(btnFechar, BorderLayout.EAST);

           
            JPanel painelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 20));
            painelSuperior.setBackground(new Color(20, 28, 70));
            JButton btnSaldo = new JButton("Saldo");
            JButton btnLimpar = new JButton("Limpeza de dados");
            painelSuperior.add(btnSaldo);
            painelSuperior.add(btnLimpar);

            topPanel.add(painelSuperior, BorderLayout.SOUTH);
            frame.add(topPanel, BorderLayout.NORTH);

            
            JPanel painelBotoes = new JPanel(new GridLayout(1, 2, 40, 10));
            painelBotoes.setBackground(new Color(100, 120, 190));
            JButton btnReceita = new JButton("Adicionar Receita");
            btnReceita.setFont(new Font("Arial", Font.PLAIN, 28));
            JButton btnDespesa = new JButton("Adicionar Despesa");
            btnDespesa.setFont(new Font("Arial", Font.PLAIN, 28));
            painelBotoes.add(btnReceita);
            painelBotoes.add(btnDespesa);
            painelBotoes.setBorder(BorderFactory.createEmptyBorder(30, 60, 30, 60));

            JPanel painelBotoes2 = new JPanel(new GridLayout(1, 2, 40, 10));
            painelBotoes2.setBackground(new Color(100, 120, 190));
            JButton btnCompras = new JButton("Compras do mês");
            btnCompras.setFont(new Font("Arial", Font.PLAIN, 28));
            JButton btnListar = new JButton("Listar Lançamentos");
            btnListar.setFont(new Font("Arial", Font.PLAIN, 28));
            painelBotoes2.add(btnCompras);
            painelBotoes2.add(btnListar);
            painelBotoes2.setBorder(BorderFactory.createEmptyBorder(30, 60, 30, 60));

            JPanel painelCentral = new JPanel(new GridLayout(2, 1));
            painelCentral.add(painelBotoes);
            painelCentral.add(painelBotoes2);

            frame.add(painelCentral, BorderLayout.CENTER);

            // Painel de gráficos
            JPanel painelGraficos = new JPanel(new GridLayout(1, 2));
            JPanel graficoGastos = new JPanel(new GridBagLayout());
            graficoGastos.setBackground(new Color(20, 28, 70));
            JLabel lblGastos = new JLabel("Gráfico de gastos");
            lblGastos.setForeground(Color.WHITE);
            lblGastos.setFont(new Font("Arial", Font.PLAIN, 28));
            graficoGastos.add(lblGastos);

            JPanel graficoFundo = new JPanel(new GridBagLayout());
            graficoFundo.setBackground(new Color(20, 28, 70));
            JLabel lblFundo = new JLabel("Gráfico de fundo");
            lblFundo.setForeground(Color.WHITE);
            lblFundo.setFont(new Font("Arial", Font.PLAIN, 28));
            graficoFundo.add(lblFundo);

            painelGraficos.add(graficoGastos);
            painelGraficos.add(graficoFundo);

            frame.add(painelGraficos, BorderLayout.SOUTH);

            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            // Controlador
            FinanceController controller = new FinanceController();

            btnSaldo.addActionListener(e -> {
                double saldo = controller.getSaldo();
                JOptionPane.showMessageDialog(frame, "Saldo atual: R$" + String.format("%.2f", saldo), "Saldo", JOptionPane.INFORMATION_MESSAGE);
            });

            btnLimpar.addActionListener(e -> {
                int confirm = JOptionPane.showConfirmDialog(frame, "Deseja limpar seus dados?", "Confirmação", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    controller.limparLancamentos();
                    JOptionPane.showMessageDialog(frame, "Dados limpos");
                }
            });

            btnReceita.addActionListener(e -> {
                String desc = JOptionPane.showInputDialog(frame, "Descrição da Receita");
                String val = JOptionPane.showInputDialog(frame, "Valor da receita");
                String categoria = JOptionPane.showInputDialog(frame, "Categoria da receita (Salário, investimentos, etc)");
                if (desc != null && val != null && categoria != null) {
                    try {
                        double valor = Double.parseDouble(val);
                        controller.adicionarLancamento(new Lancamento(desc, valor, LocalDate.now(), true, categoria));
                        JOptionPane.showMessageDialog(frame, "Receita adicionada");
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Valor errado");
                    }
                }
            });

            btnDespesa.addActionListener(e -> {
                String desc = JOptionPane.showInputDialog(frame, "Descrição da despesa");
                String val = JOptionPane.showInputDialog(frame, "Valor da despesa");
                String categoria = JOptionPane.showInputDialog(frame, "Categoria da despesa (Alimentação, Transporte, etc)");
                if (desc != null && val != null && categoria != null) {
                    try {
                        double valor = Double.parseDouble(val);
                        controller.adicionarLancamento(new Lancamento(desc, valor, LocalDate.now(), false, categoria));
                        JOptionPane.showMessageDialog(frame, "Despesa adicionada");
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Valor errado");
                    }
                }
            });

            btnListar.addActionListener(e -> {
                StringBuilder sb = new StringBuilder();
                for (Lancamento l : controller.getLancamentos()) {
                    sb.append(l.getData())
                        .append(" | ")
                        .append(l.getDescricao())
                        .append(" | R$")
                        .append(String.format("%.2f", l.getValor()))
                        .append(" |")
                        .append(l.isReceita() ? " Receita" : " Despesa")
                        .append(" | ")
                        .append(l.getCategoria())
                        .append("\n");
                }
                if (sb.length() == 0) {
                    sb.append("Nenhum lançamento encontrado");
                }
                JOptionPane.showMessageDialog(frame, sb.toString(), "Lançamentos", JOptionPane.INFORMATION_MESSAGE);
            });

           //Compras do mês//rs
            btnCompras.addActionListener(e -> {
                java.util.List<Lancamento> compras = controller.getComprasDoMes();
                StringBuilder sb = new StringBuilder();
                for (Lancamento l : compras) {
                    sb.append(l.getData())
                        .append(" | ")
                        .append(l.getDescricao())
                        .append(" | R$")
                        .append(String.format("%.2f", l.getValor()))
                        .append(" | ")
                        .append(l.getCategoria())
                        .append("\n");
                }
                if (sb.length() == 0) {
                    sb.append("Nenhuma compra encontrada para este mês.");
                }
                JOptionPane.showMessageDialog(frame, sb.toString(), "Compras do mês", JOptionPane.INFORMATION_MESSAGE);
            });
        });
    }
}