package com.sidapps.moviediscourse.model;

public class Movie {
    private int id;
    private String name;
    private String thumbnail;
    private String director;
    private String writer;
    private String youtubeTrailer;
    private String ageRating;
    private String releaseDate;
    private String tags;
    private String shortDescription;
    private double rating;

    public Movie(int id, String name, String thumbnail, String director, String writer, 
                 String youtubeTrailer, String ageRating, String releaseDate, String tags, 
                 String shortDescription, double rating) {
        this.id = id;
        this.name = name;
        this.thumbnail = thumbnail;
        this.director = director;
        this.writer = writer;
        this.youtubeTrailer = youtubeTrailer;
        this.ageRating = ageRating;
        this.releaseDate = releaseDate;
        this.tags = tags;
        this.shortDescription = shortDescription;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getYoutubeTrailer() {
        return youtubeTrailer;
    }

    public void setYoutubeTrailer(String youtubeTrailer) {
        this.youtubeTrailer = youtubeTrailer;
    }

    public String getAgeRating() {
        return ageRating;
    }

    public void setAgeRating(String ageRating) {
        this.ageRating = ageRating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
