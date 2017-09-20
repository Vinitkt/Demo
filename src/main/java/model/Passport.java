package model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
public class Passport {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private   int   passportNumber;
	@Temporal(TemporalType.DATE)
	private   Date   expireDate;
	
	@OneToOne(cascade=CascadeType.ALL)
	//@PrimaryKeyJoinColumn
    @JoinColumn(name = "persionId")

	private   Person   person;
			
	public int getPassportNumber() {
		return passportNumber;
	}
	public void setPassportNumber(int passportNumber) {
		this.passportNumber = passportNumber;
	}
	public Date getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public Passport() {
		super();
	}
	
	
}
