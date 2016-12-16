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

	// E�ere hic bi �ey belirtmezsek hibernate
	// default de�erleriyle column olu�turur
	private String city;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "location")
	private List<Department> deparments;

	// @OneToMany
	// 1'e n(�ok) , Bir locationda birden fazla department olabilir
	// Bu bir �ok departman� bir collection yap�s�nda tutmam�z gerekir.
	// �rne�in City'i Kocaeli olan bir locationda 5 tane departman olsun
	// departmanlar� list yap�s�nda tutaca��z.

	// Cascade
	// Bu locationda bir de�i�iklik yapt�g�m�zda ona ba�l� birimlerin
	// yani burdaki �rnekte departmanlarda nas�l de�i�iklikler olaca��n�
	// belirtiyoruz.
	// �rne�in Location�m�z�n �ehri Kocaeli olsun bu locationda bulunan
	// departmanlar�m�zda software , test ve network olsun
	// Bu locationda de�i�iklik yapt�g�m�zda buradan departmanlar�n ne kadar
	// etkilenee�ini belirtiyoruz

	// Burada locationdaki g�ncelleme i�lemleri i�in o locationdaki bulunan
	// departmanlardaka g�ncellemesine Cascade.ALL kullanarak izin verdik
	// e�er bu location� silersek bu locationa ba�l� t�m departmanlarada silme
	// izini verilir. E�er silme izini vermezsek ve bu location� silersek
	// veritaban�ndan hata al�r�z.

	// MappedBy
	// Bunu kullanarak hangi class'�n birinci s�n�f oldugunu belirtiyoruz
	// Nedeni : departman  tablosunda location foreign keyini eklemesini s�yledik

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
