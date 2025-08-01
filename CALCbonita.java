/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.calculadora;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CALCbonita extends JFrame implements ActionListener {

    private JTextField display;
    private String operador;
    private double numero1, numero2, resultado;
    private boolean novoNumero = true;

    public CALCbonita() {
        setTitle("calc bnt vini-jheimson-nyllay-matias");
        setSize(350, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Display
        display = new JTextField("0");
        display.setFont(new Font("Segoe UI", Font.BOLD, 32));
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setEditable(false);
        display.setBackground(Color.WHITE);
        display.setForeground(new Color(33, 33, 33));
        display.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        add(display, BorderLayout.NORTH);

        // Painel de botões
        JPanel painel = new JPanel(new GridLayout(5, 4, 10, 10));
        painel.setBackground(new Color(245, 245, 245));
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] botoes = {
            "C", "+", "-", "=",
            "7", "8", "9", "X",
            "4", "5", "6", "/",
            "1", "2", "3", "0", 
        };

        for (String texto : botoes) {
            if (texto.isEmpty()) {
                painel.add(new JLabel()); // Espaço vazio
                continue;
            }

            JButton botao = new JButton(texto);
            botao.setFont(new Font("Segoe UI", Font.BOLD, 22));
            botao.setFocusPainted(false);
            botao.setBackground(new Color(230, 230, 230));
            botao.setForeground(new Color(50, 50, 50));
            botao.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
            botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
            botao.addActionListener(this);

            // Cor especial para botões de operação
            if ("/X-+=C".contains(texto)) {
                botao.setBackground(new Color(173, 216, 230));
                botao.setForeground(Color.BLACK);
            }

            painel.add(botao);
        }

        add(painel, BorderLayout.CENTER);
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
        
    }

    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        if ("0123456789".contains(comando)) {
            if (novoNumero || display.getText().equals("0")) {
                display.setText(comando);
            } else {
                display.setText(display.getText() + comando);
            }
            novoNumero = false;
        } else if ("+-*/".contains(comando)) {
            numero1 = Double.parseDouble(display.getText());
            operador = comando;
            novoNumero = true;
        } else if ("=".equals(comando)) {
            numero2 = Double.parseDouble(display.getText());
            switch (operador) {
                case "+": resultado = numero1 + numero2; break;
                case "-": resultado = numero1 - numero2; break;
                case "*": resultado = numero1 * numero2; break;
                case "/":
                    if (numero2 == 0) {
                        JOptionPane.showMessageDialog(this, "Erro: Divisão por zero");
                        display.setText("0");
                        return;
                    }
                    resultado = numero1 / numero2; break;
            }
            display.setText(String.valueOf(resultado));
            novoNumero = true;
        } else if ("C".equals(comando)) {
            display.setText("0");
            numero1 = 0;
            numero2 = 0;
            operador = "";
            novoNumero = true;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CALCbonita::new);
    }
}