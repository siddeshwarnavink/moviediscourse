package com.sidapps.moviediscourse;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import com.sidapps.moviediscourse.dao.MovieDAO;
import com.sidapps.moviediscourse.model.Movie;

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
            JMenu movieMenu = new JMenu("Movie");
            JMenuItem createMovieItem = new JMenuItem("Create Movie");
            JMenuItem exitItem = new JMenuItem("Exit");

            movieMenu.add(createMovieItem);
            fileMenu.add(exitItem);
            menuBar.add(fileMenu);
            menuBar.add(movieMenu);
            frame.setJMenuBar(menuBar);

            // Movie List
            DefaultListModel<Movie> listModel = new DefaultListModel<>();
            List<Movie> movies = movieDAO.getAllMovies();
            movies.forEach(listModel::addElement);

            JList<Movie> movieList = new JList<>(listModel);
            movieList.setCellRenderer(new MovieListRenderer());

            // Editor Panel
            UpdateMoviePanel editorPanel = new UpdateMoviePanel();

            // Main Panel
            JPanel mainPanel = new JPanel(new BorderLayout());
            mainPanel.add(new JScrollPane(movieList), BorderLayout.WEST);
            mainPanel.add(editorPanel, BorderLayout.CENTER);

            frame.add(mainPanel, BorderLayout.CENTER);

            // Action Listeners
            createMovieItem.addActionListener(e -> {
                CreateMovieDialog movieDialog = new CreateMovieDialog(frame);
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
                        editorPanel.setEditable(false); 
                    }
                }
            });

            frame.setVisible(true);
        });
    }
}

class MovieListRenderer extends DefaultListCellRenderer {
    private static final long serialVersionUID = 5927989692178771610L;

	@Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        if (value instanceof Movie) {
            value = ((Movie) value).getName();
        }
        return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
    }
}