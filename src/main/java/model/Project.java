package model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Project
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int projectId;
	private String projectName;
	private String projectDomain;
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Emp_project", joinColumns = @JoinColumn(name = "Project_Id",
    referencedColumnName = "projectId"),
    inverseJoinColumns = @JoinColumn(name = "Emp_id", referencedColumnName = "empId"))
	private List<Employee> employee;
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectDomain() {
		return projectDomain;
	}
	public void setProjectDomain(String projectDomain) {
		this.projectDomain = projectDomain;
	}
	public List<Employee> getEmployee() {
		return employee;
	}
	public void setEmployee(List<Employee> employee) {
		this.employee = employee;
	}
	public Project(String projectName, String projectDomain, List<Employee> employee) {
		super();
		this.projectName = projectName;
		this.projectDomain = projectDomain;
		this.employee = employee;
	}
	public Project() {
		}
	
}
