package com.sidapps.moviediscourse;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Admin - Movie Discourse");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setLayout(new BorderLayout());

            // Initialize MovieDAO
            MovieDAO movieDAO = new MovieDAO();

            // Menu Bar
            JMenuBar menuBar = new JMenuBar();
            JMenu fileMenu = new JMenu("File");
            JMenuItem createMovieItem = new JMenuItem("Create Movie");
            JMenuItem exitItem = new JMenuItem("Exit");

            fileMenu.add(createMovieItem);
            fileMenu.add(exitItem);
            menuBar.add(fileMenu);
            frame.setJMenuBar(menuBar);

            // Movie List
            DefaultListModel<Movie> listModel = new DefaultListModel<>();
            List<Movie> movies = movieDAO.getAllMovies();
            movies.forEach(listModel::addElement);

            JList<Movie> movieList = new JList<>(listModel);
            movieList.setCellRenderer(new MovieListRenderer());

            // Editor Panel
            EditorPanel editorPanel = new EditorPanel();

            // Main Panel
            JPanel mainPanel = new JPanel(new BorderLayout());
            mainPanel.add(new JScrollPane(movieList), BorderLayout.WEST);
            mainPanel.add(editorPanel, BorderLayout.CENTER);

            frame.add(mainPanel, BorderLayout.CENTER);

            // Action Listeners
            createMovieItem.addActionListener(e -> {
                MovieDialog movieDialog = new MovieDialog(frame);
                movieDialog.setVisible(true);
                Movie newMovie = movieDialog.getMovie();
                if (newMovie != null) {
                    movieDAO.createMovie(newMovie);
                    listModel.addElement(newMovie);
                }
            });

            exitItem.addActionListener(e -> frame.dispose());

            movieList.addListSelectionListener(e -> {
                if (!e.getValueIsAdjusting()) {
                    Movie selectedMovie = movieList.getSelectedValue();
                    if (selectedMovie != null) {
                        editorPanel.setMovie(selectedMovie);
                        editorPanel.setEditable(true); 
                    }
                }
            });

            frame.setVisible(true);
        });
    }
}