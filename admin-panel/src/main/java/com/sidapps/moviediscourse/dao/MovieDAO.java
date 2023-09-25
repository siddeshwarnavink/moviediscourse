package com.sidapps.moviediscourse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sidapps.moviediscourse.model.Movie;

public class MovieDAO extends DAO<Movie> {
	@Override
	protected Movie mapResultSetToEntity(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt("id");
		String name = resultSet.getString("name");
		String thumbnail = resultSet.getString("thumbnail");
		String director = resultSet.getString("director");
		String writer = resultSet.getString("writer");
		String youtubeTrailer = resultSet.getString("youtube_trailer");
		String ageRating = resultSet.getString("age_rating");
		String releaseDate = resultSet.getString("release_date");
		String tags = resultSet.getString("tags");
		String shortDescription = resultSet.getString("short_description");
		double rating = resultSet.getDouble("rating");

		return new Movie(id, name, thumbnail, director, writer, youtubeTrailer, ageRating, releaseDate, tags,
				shortDescription, rating);
	}

	@Override
	protected String getTableName() {
		return "movies";
	}

	public void createMovie(Movie movie) {
		String sql = "INSERT INTO movies(name, thumbnail, director, writer, youtube_trailer, "
				+ "age_rating, release_date, tags, short_description, rating, created_at, updated_at) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW())";

		try (Connection connection = connect();
				PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			statement.setString(1, movie.getName());
			statement.setString(2, movie.getThumbnail());
			statement.setString(3, movie.getDirector());
			statement.setString(4, movie.getWriter());
			statement.setString(5, movie.getYoutubeTrailer());
			statement.setString(6, movie.getAgeRating());
			statement.setString(7, movie.getReleaseDate());
			statement.setString(8, movie.getTags());
			statement.setString(9, movie.getShortDescription());
			statement.setDouble(10, movie.getRating());

			int affectedRows = statement.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("Creating movie failed, no rows affected.");
			}

			try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					int id = generatedKeys.getInt(1);
					movie.setId(id);
				} else {
					throw new SQLException("Creating movie failed, no ID obtained.");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateMovie(Movie movie) {
		String sql = "UPDATE movies SET name=?, thumbnail=?, director=?, writer=?, youtube_trailer=?, "
				+ "age_rating=?, release_date=?, tags=?, short_description=?, rating=?, updated_at=NOW() "
				+ "WHERE id=?";
		try (Connection connection = connect(); PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setString(1, movie.getName());
			statement.setString(2, movie.getThumbnail());
			statement.setString(3, movie.getDirector());
			statement.setString(4, movie.getWriter());
			statement.setString(5, movie.getYoutubeTrailer());
			statement.setString(6, movie.getAgeRating());
			statement.setString(7, movie.getReleaseDate());
			statement.setString(8, movie.getTags());
			statement.setString(9, movie.getShortDescription());
			statement.setDouble(10, movie.getRating());
			statement.setInt(11, movie.getId());

			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Movie> getAllMovies() {
		List<Movie> movies = new ArrayList<>();
		String sql = "SELECT * FROM movies";

		try (Connection connection = connect();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(sql)) {

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String thumbnail = resultSet.getString("thumbnail");
				String director = resultSet.getString("director");
				String writer = resultSet.getString("writer");
				String youtubeTrailer = resultSet.getString("youtube_trailer");
				String ageRating = resultSet.getString("age_rating");
				String releaseDate = resultSet.getString("release_date");
				String tags = resultSet.getString("tags");
				String shortDescription = resultSet.getString("short_description");
				double rating = resultSet.getDouble("rating");

				Movie movie = new Movie(id, name, thumbnail, director, writer, youtubeTrailer, ageRating, releaseDate,
						tags, shortDescription, rating);
				movies.add(movie);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return movies;
	}

	public void deleteMovie(Movie movie) {
		String sql = "DELETE FROM movies WHERE id = ?";
		try (Connection connection = connect(); PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setInt(1, movie.getId());

			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
