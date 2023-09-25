package com.sidapps.moviediscourse;

public class Movie {
    int id;
    String name;
    String thumbnail;
    String director;
    String writer;
    String youtubeTrailer;
    String ageRating;
    String releaseDate;
    String tags;
    String shortDescription;
    double rating;

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
}
