package main.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.model.Employee;
import main.repository.EmployeeRepository;

@Service
public class EmployeeServiceDB implements EmployeeService<Employee>{	
	
	@Autowired
    private EmployeeRepository productRepository;
	
	@Override
	public void add(Employee product){
		this.productRepository.save(product);
	}
	
	@Override
	public void remove(int num){
		this.productRepository.delete(num);
	}
	
	@Override
	public Employee get(int num){
		return this.productRepository.findOne(num);
	}

	@Override
	public Iterable<Employee> findAll() {	
		return this.productRepository.findAll();
	}
}
