package com.example.appinventiv.DTO;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

public class MovieDTO {
	private Long id;
	@NotNull(message = "category field is mandatory")
    private String title;
	
	@NotNull(message = "category field is mandatory")
    private String category;
	
	@NotNull
	@DecimalMin(value="0.5", message = "star rating should not be less than 0.5")
	@DecimalMax(value="5.0", message = "star rating should not greater than 5.0")
    private Double starRating;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Double getStarRating() {
		return starRating;
	}
	public void setStarRating(Double starRating) {
		this.starRating = starRating;
	}
	@Override
	public String toString() {
		return "MovieDTO [id=" + id + ", title=" + title + ", category=" + category + ", starRating=" + starRating
				+ "]";
	}
	public MovieDTO(Long id, @NotNull(message = "category field is mandatory") String title,
			@NotNull(message = "category field is mandatory") String category,
			@NotNull @DecimalMin(value = "0.5", message = "star rating should not be less than 0.5") @DecimalMax(value = "5.0", message = "star rating should not greater than 5.0") Double starRating) {
		super();
		this.id = id;
		this.title = title;
		this.category = category;
		this.starRating = starRating;
	}
	public MovieDTO(@NotNull(message = "category field is mandatory") String title,
			@NotNull(message = "category field is mandatory") String category,
			@NotNull @DecimalMin(value = "0.5", message = "star rating should not be less than 0.5") @DecimalMax(value = "5.0", message = "star rating should not greater than 5.0") Double starRating) {
		super();
		this.title = title;
		this.category = category;
		this.starRating = starRating;
	}
	
	
    
}
