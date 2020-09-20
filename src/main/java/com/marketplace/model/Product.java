package com.marketplace.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue
	private long id;
	private String title;
	
	@Lob
	private String description;

	private float price;
	
	private String image;
	
	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.REMOVE}, fetch=FetchType.EAGER)
	@JoinColumn(name = "category_id")
	private Category category;

	@ManyToOne(cascade = {CascadeType.ALL})
	private UserEnt  seller;

	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.REMOVE}, fetch=FetchType.EAGER)
	@JoinColumn(name = "city_id")
	private City city;
	

	//producto puede estar comprado por compra
		@ManyToOne
		private Purchase purchase;
		
		@Temporal(TemporalType.TIMESTAMP)
		private Date date;

	public Product() {
	}



	


	public Product(String title, String description, float price, String image, Category category, UserEnt seller,
			City city, Purchase purchase, Date date) {
		super();
		this.title = title;
		this.description = description;
		this.price = price;
		this.image = image;
		this.category = category;
		this.seller = seller;
		this.city = city;
		this.purchase = purchase;
		this.date = date;
	}






	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}






	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}






	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}



	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}



	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}



	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}



	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}



	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}



	/**
	 * @return the price
	 */
	public float getPrice() {
		return price;
	}



	/**
	 * @param price the price to set
	 */
	public void setPrice(float price) {
		this.price = price;
	}



	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}



	/**
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}



	/**
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}



	/**
	 * @param category the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
	}



	/**
	 * @return the seller
	 */
	public UserEnt getSeller() {
		return seller;
	}



	/**
	 * @param seller the seller to set
	 */
	public void setSeller(UserEnt seller) {
		this.seller = seller;
	}



	/**
	 * @return the city
	 */
	public City getCity() {
		return city;
	}



	/**
	 * @param city the city to set
	 */
	public void setCity(City city) {
		this.city = city;
	}



	/**
	 * @return the purchase
	 */
	public Purchase getPurchase() {
		return purchase;
	}



	/**
	 * @param purchase the purchase to set
	 */
	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((image == null) ? 0 : image.hashCode());
		result = prime * result + Float.floatToIntBits(price);
		result = prime * result + ((purchase == null) ? 0 : purchase.hashCode());
		result = prime * result + ((seller == null) ? 0 : seller.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (image == null) {
			if (other.image != null)
				return false;
		} else if (!image.equals(other.image))
			return false;
		if (Float.floatToIntBits(price) != Float.floatToIntBits(other.price))
			return false;
		if (purchase == null) {
			if (other.purchase != null)
				return false;
		} else if (!purchase.equals(other.purchase))
			return false;
		if (seller == null) {
			if (other.seller != null)
				return false;
		} else if (!seller.equals(other.seller))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}



	
	
	
	
	
	
	
	
	
}
