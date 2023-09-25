package com.sidapps.moviediscourse;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.sidapps.moviediscourse.model.Movie;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateMovieDialog extends JDialog {
    private static final long serialVersionUID = -2105262483778361251L;
    
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

    public CreateMovieDialog(JFrame owner) {
        super(owner, "Create Movie", true);
        setLayout(new BorderLayout());

        JPanel fieldsPanel = new JPanel(new GridLayout(0, 2));
        fieldsPanel.setBorder(new EmptyBorder(10, 10, 0, 10));
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        
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
        fieldsPanel.add(new JLabel("Name:")); fieldsPanel.add(nameField);
        fieldsPanel.add(new JLabel("Thumbnail:")); fieldsPanel.add(thumbnailField);
        fieldsPanel.add(new JLabel("Director:")); fieldsPanel.add(directorField);
        fieldsPanel.add(new JLabel("Writer:")); fieldsPanel.add(writerField);
        fieldsPanel.add(new JLabel("Youtube Trailer:")); fieldsPanel.add(youtubeTrailerField);
        fieldsPanel.add(new JLabel("Age Rating:")); fieldsPanel.add(ageRatingField);
        fieldsPanel.add(new JLabel("Release Date:")); fieldsPanel.add(releaseDateField);
        fieldsPanel.add(new JLabel("Tags:")); fieldsPanel.add(tagsField);
        fieldsPanel.add(new JLabel("Short Description:")); fieldsPanel.add(new JScrollPane(shortDescriptionArea));
        fieldsPanel.add(new JLabel("Rating:")); fieldsPanel.add(ratingField);
        
        buttonsPanel.add(okButton);
        buttonsPanel.add(cancelButton);

        add(fieldsPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);

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
