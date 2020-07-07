package main.services;

import main.model.Employee;

public interface EmployeeService<T> {

	public void add(T element);
	
	public void remove(int num);
	
	public Employee get(int num);

	public Iterable<T> findAll();
}
