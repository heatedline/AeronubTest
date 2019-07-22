package com.aeronube.supra.AeronubeTest.dto;

import java.util.Date;
import java.util.List;

import com.aeronube.supra.AeronubeTest.model.Review;

public class BookDTO {
	
	private int id;
	private String title;
	private String author;
	private Date publishedDate;
	private String isbn;
	private List<Review> reviews;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Date getPublishedDate() {
		return publishedDate;
	}
	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public List<Review> getReviews() {
		return reviews;
	}
	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	@Override
	public String toString() {
		return "BookDTO [id=" + id + ", title=" + title + ", author=" + author + ", publishedDate=" + publishedDate
				+ ", isbn=" + isbn + ", reviews=" + reviews + "]";
	}
	
	
	
}
