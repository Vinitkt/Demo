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
public class IdCard {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private int idNumber;
@Temporal(TemporalType.DATE)
private Date issueDate;
@Temporal(TemporalType.DATE)
private Date expireDate;
@OneToOne(cascade=CascadeType.ALL)
@JoinColumn(name = "empId")
private Employee employee;
public int getIdNumber() {
	return idNumber;
}
public void setIdNumber(int idNumber) {
	this.idNumber = idNumber;
}
public Date getIssueDate() {
	return issueDate;
}
public void setIssueDate(Date issueDate) {
	this.issueDate = issueDate;
}
public Date getExpireDate() {
	return expireDate;
}
public void setExpireDate(Date expireDate) {
	this.expireDate = expireDate;
}
public Employee getEmployee() {
	return employee;
}
public void setEmployee(Employee employee) {
	this.employee = employee;
}
public IdCard() {
	
}

}
