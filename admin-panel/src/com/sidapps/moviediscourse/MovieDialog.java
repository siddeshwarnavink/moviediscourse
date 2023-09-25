package com.sidapps.moviediscourse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MovieDialog extends JDialog {
    private JTextField nameField;
    private JTextField thumbnailField;
    private JTextField directorField;
    private JTextField writerField;
    private JTextField youtubeTrailerField;
    private JTextField ageRatingField;
    private JTextField releaseDateField;
    private JTextField tagsField;
    private JTextArea shortDescriptionArea;
    private JTextField ratingField;
    
    private JButton okButton;
    private JButton cancelButton;
    
    private Movie movie;

    public MovieDialog(JFrame owner) {
        super(owner, "Create Movie", true);
        setLayout(new GridLayout(0, 2));
        
        // Initialize fields
        nameField = new JTextField(20);
        thumbnailField = new JTextField(20);
        directorField = new JTextField(20);
        writerField = new JTextField(20);
        youtubeTrailerField = new JTextField(20);
        ageRatingField = new JTextField(20);
        releaseDateField = new JTextField(20);
        tagsField = new JTextField(20);
        shortDescriptionArea = new JTextArea(3, 20);
        ratingField = new JTextField(20);
        
        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");

        // Add labels and fields to dialog
        add(new JLabel("Name:")); add(nameField);
        add(new JLabel("Thumbnail:")); add(thumbnailField);
        add(new JLabel("Director:")); add(directorField);
        add(new JLabel("Writer:")); add(writerField);
        add(new JLabel("Youtube Trailer:")); add(youtubeTrailerField);
        add(new JLabel("Age Rating:")); add(ageRatingField);
        add(new JLabel("Release Date:")); add(releaseDateField);
        add(new JLabel("Tags:")); add(tagsField);
        add(new JLabel("Short Description:")); add(new JScrollPane(shortDescriptionArea));
        add(new JLabel("Rating:")); add(ratingField);
        
        add(okButton); add(cancelButton);

        pack();

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
        
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });
    }

    private void onOK() {
        movie = new Movie(0, nameField.getText(), thumbnailField.getText(), directorField.getText(), 
                          writerField.getText(), youtubeTrailerField.getText(), ageRatingField.getText(), 
                          releaseDateField.getText(), tagsField.getText(), shortDescriptionArea.getText(),
                          Double.parseDouble(ratingField.getText()));
        dispose();
    }

    private void onCancel() {
        movie = null;
        dispose();
    }

    public Movie getMovie() {
        return movie;
    }
}
