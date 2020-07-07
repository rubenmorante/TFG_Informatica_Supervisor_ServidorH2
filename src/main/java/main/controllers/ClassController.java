package main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import main.model.Employee;
import main.services.EmployeeServiceDB;

@RestController
public class ClassController {
	
	/** Service Element */
	@Autowired
	private EmployeeServiceDB employeeService;
	
	/** to add to the database */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void add(@RequestBody Employee employee) {
		this.employeeService.add(employee);
	}
	
	/** to return the list of the database */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Iterable<Employee> list() {
		return this.employeeService.findAll();
	}
	
	/** to get from the database */
	@RequestMapping(value = "/show/{numEmployee}", method = RequestMethod.GET)
	public Employee get(@PathVariable int numEmployee) {
		return this.employeeService.get(numEmployee);
	}
	
	/** to delete from the database */
	@RequestMapping(value = "/delete/{numEmployee}", method = RequestMethod.GET)
	public void delete(@PathVariable int numEmployee) {
		this.employeeService.remove(numEmployee);
	}
}