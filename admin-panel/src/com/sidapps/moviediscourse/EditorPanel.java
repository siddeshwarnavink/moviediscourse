package com.sidapps.moviediscourse;

import javax.swing.*;
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
    private Movie displayedMovie;
    private final MovieDAO movieDAO = new MovieDAO();

    public EditorPanel() {
        setLayout(new GridLayout(0, 2));

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

        add(new JLabel("Name:"));
        add(nameField);
        add(new JLabel("Thumbnail:"));
        add(thumbnailField);
        add(new JLabel("Director:"));
        add(directorField);
        add(new JLabel("Writer:"));
        add(writerField);
        add(new JLabel("Youtube Trailer:"));
        add(youtubeTrailerField);
        add(new JLabel("Age Rating:"));
        add(ageRatingField);
        add(new JLabel("Release Date:"));
        add(releaseDateField);
        add(new JLabel("Tags:"));
        add(tagsField);
        add(new JLabel("Rating:"));
        add(ratingField);
        add(new JLabel("Short Description:"));
        add(new JScrollPane(shortDescriptionArea));
        add(saveButton);

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
        saveButton.setVisible(editable);
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
