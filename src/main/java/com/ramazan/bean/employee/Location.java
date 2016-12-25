package com.ramazan.bean.employee;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
// @NamedQuery ile bu sekilde tanimlanan sorgular compile sirasinda cacheleniyor
// ben bu sorgulardan birini cagirdigimda ise normalden daha hizli ulasiyorum.
@NamedQueries({
		@NamedQuery(name = "Location.findAll", query = "SELECT l FROM Location l"),
		@NamedQuery(name = "Location.findDepartmentById", query = "SELECT l FROM Location l"
				+ " LEFT OUTER JOIN FETCH l.departments WHERE l.location.Id = :locationId")
// :locationId , demek sana disaridan verecegim Id ile gidip sorguyu
// calistiracagim demektir.
})
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
	// 1'e n(cok) , Bir locationda birden fazla department olabilir
	// Bu bir cok departmani bir collection yapisinda tutmamiz gerekir.
	// ornegin City'i Kocaeli olan bir locationda 5 tane departman olsun
	// departmanlari list yapisinda tutacagiz.

	// Cascade
	// Bu locationda bir degisiklik yaptigimizda ona bagli birimlerin
	// yani burdaki ornekte departmanlarda nasil degisiklikler olacagini
	// belirtiyoruz.
	// Ornegin Locationimizinn sehri Kocaeli olsun bu locationda bulunan
	// departmanlarimizda software , test ve network olsun
	// Bu locationda degisiklik yaptigimizda buradan departmanlarin ne kadar
	// etkilenecegini belirtiyoruz

	// Burada locationdaki guncelleme islemleri icin o locationdaki bulunan
	// departmanlardaka guncellemesine Cascade.ALL kullanarak izin verdik
	// eger bu locationi silersek bu locationa bagli tum departmanlarada silme
	// izini verilir. Eger silme izini vermezsek ve bu locationi silersek
	// veritabanindan hata aliriz.

	// MappedBy
	// Bunu kullanarak hangi class'in birinci sinif oldugunu belirtiyoruz
	// Nedeni : departman tablosunda location foreign keyini eklemesini soyledik

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
