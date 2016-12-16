package com.ramazan.bean.user;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
public class PasswordResetToken {

	@Transient // bu parametre için kolon oluþturma diyorum.
	private final int EXPIRY_DATE = 60 * 24;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	private String token;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id",foreignKey = @ForeignKey(foreignKeyDefinition = "user_fk"))
	private User user;

	@Temporal(TemporalType.TIMESTAMP) // tarih ile birlikte saatide tutmak için timestamp kullandým
	private Date expiryDate;

	public PasswordResetToken() {
		this.expiryDate = calculateExpiryDate(EXPIRY_DATE);
	}

	public PasswordResetToken(String token, User user) {
		this.token = token;
		this.user = user;
		this.expiryDate = calculateExpiryDate(EXPIRY_DATE);
	}

	private Date calculateExpiryDate(int EXPIRY_DATE) {

		Calendar calendar = Calendar.getInstance(); // takvim oluþtur

		calendar.setTimeInMillis(new Date().getTime()); // þu anki

		calendar.add(Calendar.MINUTE, EXPIRY_DATE); // Takvime dakika türünden
													// data eklenmesi

		return new Date(calendar.getTime().getTime());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
		PasswordResetToken other = (PasswordResetToken) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


}
