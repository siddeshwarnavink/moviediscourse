package com.sidapps.moviediscourse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Admin - Movie Discourse");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setLayout(new BorderLayout());

            // Menu Bar
            JMenuBar menuBar = new JMenuBar();
            JMenu fileMenu = new JMenu("File");
            JMenuItem createMovieItem = new JMenuItem("Create Movie");
            JMenuItem exitItem = new JMenuItem("Exit");

            fileMenu.add(createMovieItem);
            fileMenu.add(exitItem);
            menuBar.add(fileMenu);
            frame.setJMenuBar(menuBar);

            JPanel mainPanel = new JPanel(new BorderLayout());

            DefaultListModel<String> listModel = new DefaultListModel<>();
            listModel.addElement("Movie 1");
            listModel.addElement("Movie 2");
            JList<String> movieList = new JList<>(listModel);
            mainPanel.add(new JScrollPane(movieList), BorderLayout.WEST);

            JTextArea movieEditor = new JTextArea();
            mainPanel.add(new JScrollPane(movieEditor), BorderLayout.CENTER);

            frame.add(mainPanel, BorderLayout.CENTER);

            createMovieItem.addActionListener(e -> {
                String newMovie = JOptionPane.showInputDialog(frame, "Enter new movie name:");
                if (newMovie != null && !newMovie.trim().isEmpty()) {
                    listModel.addElement(newMovie);
                }
            });

            exitItem.addActionListener(e -> {
                frame.dispose();
            });

            movieList.addListSelectionListener(e -> {
                if (!e.getValueIsAdjusting()) {
                    String selectedMovie = movieList.getSelectedValue();
                    if (selectedMovie != null) {
                        movieEditor.setText(selectedMovie + " details...");
                    }
                }
            });

            frame.setVisible(true);
        });
    }
}
