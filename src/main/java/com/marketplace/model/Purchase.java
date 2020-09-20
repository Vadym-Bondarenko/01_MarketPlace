package com.marketplace.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name="purchase")
public class Purchase {

	@Id @GeneratedValue
	private long id;
	
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date datePurchase;
	
	@ManyToOne
	private UserEnt buyer;

	public Purchase(UserEnt buyer) {
		this.buyer = buyer;
	}

	public Purchase() {
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
	 * @return the datePurchase
	 */
	public Date getDatePurchase() {
		return datePurchase;
	}

	/**
	 * @param datePurchase the datePurchase to set
	 */
	public void setDatePurchase(Date datePurchase) {
		this.datePurchase = datePurchase;
	}

	/**
	 * @return the buyer
	 */
	public UserEnt getBuyer() {
		return buyer;
	}

	/**
	 * @param buyer the buyer to set
	 */
	public void setBuyer(UserEnt buyer) {
		this.buyer = buyer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((buyer == null) ? 0 : buyer.hashCode());
		result = prime * result + ((datePurchase == null) ? 0 : datePurchase.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Purchase other = (Purchase) obj;
		if (buyer == null) {
			if (other.buyer != null)
				return false;
		} else if (!buyer.equals(other.buyer))
			return false;
		if (datePurchase == null) {
			if (other.datePurchase != null)
				return false;
		} else if (!datePurchase.equals(other.datePurchase))
			return false;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
}
