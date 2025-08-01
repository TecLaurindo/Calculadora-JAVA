/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.calculadora;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class testeCalculadora2 extends JFrame implements ActionListener {
    private JTextField display;
    private double primeiroNumero;
    private String operacao;

    public testeCalculadora2() {
        // Configuração da janela
        setTitle("Calculadora Simples");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Área de display
        display = new JTextField();
        display.setFont(new Font("Arial", Font.PLAIN, 24));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        // Painel de botões
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new GridLayout(5, 4, 5, 5));

        // Botões
        String[] botoes = {
                "7", "8", "9", "/",
                "4", "5", "6", "X",
                "1", "2", "3", "-",
                "0", ".", "=", "+",
                "C", "CE"
        };

        for (String textoBotao : botoes) {
            JButton botao = new JButton(textoBotao);
            botao.setFont(new Font("Arial", Font.PLAIN, 18));
            botao.addActionListener(this);
            painelBotoes.add(botao);
        }

        add(painelBotoes, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        if (comando.matches("[0-9]")) {
            display.setText(display.getText() + comando);
        } else if (comando.equals(".")) {
            if (!display.getText().contains(".")) {
                display.setText(display.getText() + ".");
            }
        } else if (comando.matches("[+\\-*/]")) {
            primeiroNumero = Double.parseDouble(display.getText());
            operacao = comando;
            display.setText("");
        } else if (comando.equals("=")) {
            double segundoNumero = Double.parseDouble(display.getText());
            double resultado = 0;

            switch (operacao) {
                case "+":
                    resultado = primeiroNumero + segundoNumero;
                    break;
                case "-":
                    resultado = primeiroNumero - segundoNumero;
                    break;
                case "*":
                    resultado = primeiroNumero * segundoNumero;
                    break;
                case "/":
                    if (segundoNumero != 0) {
                        resultado = primeiroNumero / segundoNumero;
                    } else {
                        display.setText("Erro");
                        return;
                    }
                    break;
            }

            display.setText(String.valueOf(resultado));
        } else if (comando.equals("C")) {
            display.setText("");
        } else if (comando.equals("CE")) {
            display.setText("");
            primeiroNumero = 0;
            operacao = "";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            testeCalculadora2 calculadora = new testeCalculadora2();
            calculadora.setVisible(true);
        });
    }
}