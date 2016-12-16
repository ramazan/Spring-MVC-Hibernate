package com.ramazan.bean.employee;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Location {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long locaionId;

	@Column(length = 25)
	private String streetAdress;

	@Column(name = "postalCode")
	private int postalCode;

	// Eðere hic bi þey belirtmezsek hibernate
	// default deðerleriyle column oluþturur
	private String city;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "location")
	private List<Department> deparments;

	// @OneToMany
	// 1'e n(çok) , Bir locationda birden fazla department olabilir
	// Bu bir çok departmaný bir collection yapýsýnda tutmamýz gerekir.
	// Örneðin City'i Kocaeli olan bir locationda 5 tane departman olsun
	// departmanlarý list yapýsýnda tutacaðýz.

	// Cascade
	// Bu locationda bir deðiþiklik yaptýgýmýzda ona baðlý birimlerin
	// yani burdaki örnekte departmanlarda nasýl deðiþiklikler olacaðýný
	// belirtiyoruz.
	// Örneðin Locationýmýzýn þehri Kocaeli olsun bu locationda bulunan
	// departmanlarýmýzda software , test ve network olsun
	// Bu locationda deðiþiklik yaptýgýmýzda buradan departmanlarýn ne kadar
	// etkileneeðini belirtiyoruz

	// Burada locationdaki güncelleme iþlemleri için o locationdaki bulunan
	// departmanlardaka güncellemesine Cascade.ALL kullanarak izin verdik
	// eðer bu locationý silersek bu locationa baðlý tüm departmanlarada silme
	// izini verilir. Eðer silme izini vermezsek ve bu locationý silersek
	// veritabanýndan hata alýrýz.

	// MappedBy
	// Bunu kullanarak hangi class'ýn birinci sýnýf oldugunu belirtiyoruz
	// Nedeni : departman  tablosunda location foreign keyini eklemesini söyledik

	public Location() {

	}

	public Location(String streetAdress, int postalCode, String city) {
		this.streetAdress = streetAdress;
		this.postalCode = postalCode;
		this.city = city;
	}

	public Long getLocaionId() {
		return locaionId;
	}

	public void setLocaionId(Long locaionId) {
		this.locaionId = locaionId;
	}

	public String getStreetAdress() {
		return streetAdress;
	}

	public void setStreetAdress(String streetAdress) {
		this.streetAdress = streetAdress;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public List<Department> getDeparments() {
		return deparments;
	}

	public void setDeparments(List<Department> deparments) {
		this.deparments = deparments;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((locaionId == null) ? 0 : locaionId.hashCode());
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
		Location other = (Location) obj;
		if (locaionId == null) {
			if (other.locaionId != null)
				return false;
		} else if (!locaionId.equals(other.locaionId))
			return false;
		return true;
	}

}
