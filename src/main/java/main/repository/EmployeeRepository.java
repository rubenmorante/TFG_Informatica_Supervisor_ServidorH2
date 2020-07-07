package main.repository;

import org.springframework.data.repository.CrudRepository;

import main.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

	/** Find by employee */
	Employee findByName(String name);
}
