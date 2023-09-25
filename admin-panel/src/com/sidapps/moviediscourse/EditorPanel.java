package com.sidapps.moviediscourse;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditorPanel extends JPanel {
    private final JTextField nameField;
    private final JTextField thumbnailField;
    private final JTextField directorField;
    private final JTextField writerField;
    private final JTextField youtubeTrailerField;
    private final JTextField ageRatingField;
    private final JTextField releaseDateField;
    private final JTextField tagsField;
    private final JTextField ratingField;
    private final JTextArea shortDescriptionArea;
    private final JButton saveButton;
    private final JButton updateButton;
    private final JButton deleteButton;
    private final JButton cancelButton;
    private Movie displayedMovie;
    private final MovieDAO movieDAO = new MovieDAO();

    public EditorPanel() {
        setLayout(new BorderLayout());

        nameField = new JTextField();
        thumbnailField = new JTextField();
        directorField = new JTextField();
        writerField = new JTextField();
        youtubeTrailerField = new JTextField();
        ageRatingField = new JTextField();
        releaseDateField = new JTextField();
        tagsField = new JTextField();
        ratingField = new JTextField();
        shortDescriptionArea = new JTextArea();
        saveButton = new JButton("Save");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");
        cancelButton = new JButton("Cancel");

        JPanel fieldsPanel = new JPanel(new GridLayout(0, 2));
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(updateButton);
        buttonsPanel.add(deleteButton);
        buttonsPanel.add(saveButton);
        buttonsPanel.add(cancelButton);

        fieldsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        buttonsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        add(fieldsPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);

        fieldsPanel.add(new JLabel("Name:"));
        fieldsPanel.add(nameField);
        fieldsPanel.add(new JLabel("Thumbnail:"));
        fieldsPanel.add(thumbnailField);
        fieldsPanel.add(new JLabel("Director:"));
        fieldsPanel.add(directorField);
        fieldsPanel.add(new JLabel("Writer:"));
        fieldsPanel.add(writerField);
        fieldsPanel.add(new JLabel("Youtube Trailer:"));
        fieldsPanel.add(youtubeTrailerField);
        fieldsPanel.add(new JLabel("Age Rating:"));
        fieldsPanel.add(ageRatingField);
        fieldsPanel.add(new JLabel("Release Date:"));
        fieldsPanel.add(releaseDateField);
        fieldsPanel.add(new JLabel("Tags:"));
        fieldsPanel.add(tagsField);
        fieldsPanel.add(new JLabel("Rating:"));
        fieldsPanel.add(ratingField);
        fieldsPanel.add(new JLabel("Short Description:"));
        fieldsPanel.add(new JScrollPane(shortDescriptionArea));

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (displayedMovie != null) {
                    displayedMovie.setName(nameField.getText());
                    displayedMovie.setThumbnail(thumbnailField.getText());
                    displayedMovie.setDirector(directorField.getText());
                    displayedMovie.setWriter(writerField.getText());
                    displayedMovie.setYoutubeTrailer(youtubeTrailerField.getText());
                    displayedMovie.setAgeRating(ageRatingField.getText());
                    displayedMovie.setReleaseDate(releaseDateField.getText());
                    displayedMovie.setTags(tagsField.getText());
                    displayedMovie.setShortDescription(shortDescriptionArea.getText());
                    displayedMovie.setRating(Double.parseDouble(ratingField.getText()));
                    movieDAO.updateMovie(displayedMovie);
                    setEditable(false);
                }
            }
        });

        setEditable(false);

        updateButton.addActionListener(e -> setEditable(true));
        cancelButton.addActionListener(e -> setEditable(false));

        deleteButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this movie?",
                    "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                movieDAO.deleteMovie(displayedMovie);
                setMovie(null);
            }
        });

    }

    public void setEditable(boolean editable) {
        nameField.setEditable(editable);
        thumbnailField.setEditable(editable);
        directorField.setEditable(editable);
        writerField.setEditable(editable);
        youtubeTrailerField.setEditable(editable);
        ageRatingField.setEditable(editable);
        releaseDateField.setEditable(editable);
        tagsField.setEditable(editable);
        ratingField.setEditable(editable);
        shortDescriptionArea.setEditable(editable);

        saveButton.setVisible(displayedMovie != null && editable);
        cancelButton.setVisible(displayedMovie != null && editable);
        updateButton.setVisible(displayedMovie != null && !editable);
        deleteButton.setVisible(displayedMovie != null && !editable);
    }

    public void setMovie(Movie movie) {
        displayedMovie = movie;
        if (movie != null) {
            nameField.setText(movie.getName());
            thumbnailField.setText(movie.getThumbnail());
            directorField.setText(movie.getDirector());
            writerField.setText(movie.getWriter());
            youtubeTrailerField.setText(movie.getYoutubeTrailer());
            ageRatingField.setText(movie.getAgeRating());
            releaseDateField.setText(movie.getReleaseDate());
            tagsField.setText(movie.getTags());
            ratingField.setText(String.valueOf(movie.getRating()));
            shortDescriptionArea.setText(movie.getShortDescription());
        } else {
            // Clear the fields if the movie is null
            nameField.setText("");
            thumbnailField.setText("");
            directorField.setText("");
            writerField.setText("");
            youtubeTrailerField.setText("");
            ageRatingField.setText("");
            releaseDateField.setText("");
            tagsField.setText("");
            ratingField.setText("");
            shortDescriptionArea.setText("");
        }
    }
}
