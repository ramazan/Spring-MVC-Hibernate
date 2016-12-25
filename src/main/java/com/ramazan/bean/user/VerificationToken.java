package com.ramazan.bean.user;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "VerificationToken")
public class VerificationToken {

	@Transient
	//tokenin sona erme suresi  
	private final int EXPIRY_DATE = 60*24; // 60dk * 24 saat  

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")

	private User user;
	
	
	@Column
	private String token;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date expiryDate;
	
	public VerificationToken() {

		this.expiryDate = calculateExpiryDate(EXPIRY_DATE);
	}
	
	public VerificationToken(User user, String token) {
		this.user = user;
		this.token = token;
		this.expiryDate = calculateExpiryDate(EXPIRY_DATE);
	}

	private Date calculateExpiryDate(int EXPIRY_DATE) {
		
		Calendar calendar = Calendar.getInstance();  // takvim olustur
		
		calendar.setTimeInMillis(new Date().getTime()); // su anki 
		
		calendar.add(Calendar.MINUTE, EXPIRY_DATE); // Takvime dakika turunden data eklenmesi
		
		return new Date(calendar.getTime().getTime());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public int getEXPIRY_DATE() {
		return EXPIRY_DATE;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		VerificationToken other = (VerificationToken) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	

	
	

}
