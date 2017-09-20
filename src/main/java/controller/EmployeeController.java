package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dao.EmployeeRepository;
import model.Employee;

@Controller
public class EmployeeController 
{
	@Autowired
	public EmployeeRepository empRepository;
	@RequestMapping("/employeeForm")
	public String showemp()
	{
		return"Employee";
	}
	@RequestMapping(value="/saveEmployee",method=RequestMethod.POST)
	public String save(Employee employee,Model model)
	{
		empRepository.save(employee);
		String msg="employee saved with id"+employee.getEmpId();
		model.addAttribute("msg",msg);
		return"Employee";
	}
	}
