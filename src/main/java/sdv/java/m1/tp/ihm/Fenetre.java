package sdv.java.m1.tp.ihm;


import sdv.java.m1.tp.service.Traceur;
import sdv.java.m1.tp.service.Filtrage;
import sdv.java.m1.tp.bean.CritereFiltrage;
import sdv.java.m1.tp.enums.CriticiteAction;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Fenetre extends JFrame {
    private JTextArea textArea;
    private JTextField filterField;
    private JButton refreshButton;
    private JButton filterButton;
    private Traceur traceur;
    private Filtrage filtrage;

    public Fenetre() {
        traceur = new Traceur();
        filtrage = new Filtrage();

        setTitle("\uD83E\uDE77\uD83E\uDE77 Traceur de Logs \uD83E\uDE77\uD83E\uDE77");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
        layoutComponents();
    }

    private void initComponents() {
        textArea = new JTextArea();
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);

        filterField = new JTextField();
        refreshButton = new JButton("Actualiser");
        filterButton = new JButton("Filtrer");

        filterField.setPreferredSize(new Dimension(200, 30));


        refreshButton.addActionListener(e -> refreshLogs());
        filterButton.addActionListener(e -> filterLogs());
    }

    private void layoutComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new FlowLayout());
        northPanel.add(filterField);
        northPanel.add(refreshButton);
        northPanel.add(filterButton);

        panel.add(northPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);

        this.add(panel);
    }

    private void refreshLogs() {
        List<String> lines = traceur.lire();

        textArea.setText(String.join("\n", lines));
    }

    private void changeColor() {
        Color backgroundColor;
        do {
            Random rand = new Random();
            int r = rand.nextInt(256);
            int g = rand.nextInt(256);
            int b = rand.nextInt(256);
            backgroundColor = new Color(r, g, b);
        } while (backgroundColor.equals(Color.WHITE));

        textArea.setBackground(backgroundColor);
    }

    private void filterLogs() {
        List<String> lines = traceur.lire();
        CritereFiltrage critere = new CritereFiltrage();
        critere.setSousChaine(filterField.getText());
        critere.setCriticites(Arrays.asList(CriticiteAction.values())); // Assuming we want to filter by all criticity levels
        Stream<String> filteredLines = filtrage.filtrer(lines, critere);
        textArea.setText(String.join("\n", filteredLines.collect(Collectors.toList())));
    }

    public static void main(String[] args) throws IOException {
        EventQueue.invokeLater(() -> {
            Fenetre fenetre = new Fenetre();
            fenetre.refreshLogs();
            fenetre.setVisible(true);

            Timer timer = new Timer(100, e -> fenetre.changeColor());
            timer.start();

        });
    }
}

