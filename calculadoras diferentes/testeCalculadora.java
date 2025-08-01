/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.calculadora;

/**
 *
 * @author Aluno01
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class testeCalculadora extends JFrame implements ActionListener {

    private JTextField display;
    private String operador;
    private double numero1, numero2, resultado;
    private boolean novoNumero = true;

    public testeCalculadora() {
        setTitle("Calc NORM Vini_Jheimson_Nyllay");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Display
        display = new JTextField("0");
        display.setFont(new Font("Arial", Font.BOLD, 30));
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        // Painel de botões
        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(3, 3, 4, 4));

        String[] botoes = {
            "7", "8", "9", "/",
            "4", "5", "6", "X",
            "1", "2", "3", "-",
            "C", "0", "=", "+"
        };

        for (String texto : botoes) {
            JButton botao = new JButton(texto);
            botao.setFont(new Font("Arial", Font.BOLD, 18));
            botao.addActionListener(this);
            painel.add(botao);
        }

        add(painel, BorderLayout.CENTER);
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
        SwingUtilities.invokeLater(testeCalculadora::new);
    }
}
