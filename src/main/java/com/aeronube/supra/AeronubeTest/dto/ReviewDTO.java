package com.aeronube.supra.AeronubeTest.dto;

import java.util.Date;

public class ReviewDTO {

	private int id;
	private String reviewerName;
	private String content;
	private Double rating;
	private Date publishedDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getReviewerName() {
		return reviewerName;
	}
	public void setReviewerName(String reviewerName) {
		this.reviewerName = reviewerName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Double getRating() {
		return rating;
	}
	public void setRating(Double rating) {
		this.rating = rating;
	}
	public Date getPublishedDate() {
		return publishedDate;
	}
	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}
	@Override
	public String toString() {
		return "ReviewDTO [id=" + id + ", reviewerName=" + reviewerName + ", content=" + content + ", rating=" + rating
				+ ", publishedDate=" + publishedDate + "]";
	}
	
	
	
}
