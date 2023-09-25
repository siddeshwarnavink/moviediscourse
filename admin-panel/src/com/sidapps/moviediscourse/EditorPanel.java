package com.sidapps.moviediscourse;

import javax.swing.*;
import java.awt.*;

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
    
    public EditorPanel() {
        setLayout(new GridLayout(0, 2)); // Adjust layout manager as needed
        
        // Initialize the text fields and text area
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
        
        // Add labels and fields to the panel
        add(new JLabel("Name:")); add(nameField);
        add(new JLabel("Thumbnail:")); add(thumbnailField);
        add(new JLabel("Director:")); add(directorField);
        add(new JLabel("Writer:")); add(writerField);
        add(new JLabel("Youtube Trailer:")); add(youtubeTrailerField);
        add(new JLabel("Age Rating:")); add(ageRatingField);
        add(new JLabel("Release Date:")); add(releaseDateField);
        add(new JLabel("Tags:")); add(tagsField);
        add(new JLabel("Rating:")); add(ratingField);
        add(new JLabel("Short Description:")); add(new JScrollPane(shortDescriptionArea));
        
        // If you don't want to allow editing, set fields to non-editable
        nameField.setEditable(false);
        thumbnailField.setEditable(false);
        directorField.setEditable(false);
        writerField.setEditable(false);
        youtubeTrailerField.setEditable(false);
        ageRatingField.setEditable(false);
        releaseDateField.setEditable(false);
        tagsField.setEditable(false);
        ratingField.setEditable(false);
        shortDescriptionArea.setEditable(false);
    }
    
    public void setMovie(Movie movie) {
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
