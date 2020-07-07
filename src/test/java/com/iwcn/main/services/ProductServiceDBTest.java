package com.iwcn.main.services;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import main.model.Employee;
import main.repository.EmployeeRepository;
import main.services.EmployeeServiceDB;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceDBTest {	

	private static final int ID = 1;
	private static final String NAME = "lena soderberg";
	private static final String PHONE = "654789321";
	private static final String IMAGE = "../../images/Lenna.png";
	
//	static {
//		try {
//			IMAGE = ImageIO.read(new File("../../images/Lenna.png"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

	@Mock
	private EmployeeRepository productRepository;
	
	@InjectMocks
	private EmployeeServiceDB productServiceDB = new EmployeeServiceDB();

	private Employee employee;	
	
	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
		
		this.employee = new Employee();		
		this.employee.setId(ProductServiceDBTest.ID);
		this.employee.setName(ProductServiceDBTest.NAME);
		this.employee.setPhone(ProductServiceDBTest.PHONE);
		this.employee.setImage(ProductServiceDBTest.IMAGE);		
		
		List<Employee> productList = new ArrayList<Employee>();
		productList.add(this.employee);			
		
		when(this.productRepository.findAll()).thenReturn(productList);
		when(this.productRepository.findOne(anyInt())).thenReturn(this.employee); 
	}
	
	@Test
	public void productTest() {	
		
		assertNotNull(this.employee);
		
		assertTrue(this.employee.getClass().getSimpleName().equals("Employee"));
		assertTrue(this.employee.getId() == ProductServiceDBTest.ID);
		assertTrue(this.employee.getName().equals(ProductServiceDBTest.NAME));
		assertTrue(this.employee.getPhone().equals(ProductServiceDBTest.PHONE));
		assertTrue(this.employee.getImage() == ProductServiceDBTest.IMAGE);		
	}
	
	@Test
	public void productListTest() {
		List<Employee> productList = Lists.newArrayList(this.productServiceDB.findAll());
		assertNotNull(productList);
		assertTrue(productList.size() > 0);
		
		assertTrue(productList.get(0).getName().equals(ProductServiceDBTest.NAME));
	}
	
	@Test
	public void addTest() {
		List<Employee> productList = Lists.newArrayList(this.productServiceDB.findAll());
		when(this.productRepository.findAll()).thenReturn(productList);
		
		int size = productList.size();
		assertEquals(productList.size(), size);
		
		Employee product2 = new Employee(2, "productTwo", "descriptionTwo", "p");
		
		this.productServiceDB.add(product2);
		productList.add(product2);

		ArgumentCaptor <Employee> argumentCaptor = ArgumentCaptor.forClass(Employee.class);
		verify(this.productRepository, times(1)).save(argumentCaptor.capture());
		
		assertEquals(product2, argumentCaptor.getValue());
		assertFalse(Lists.newArrayList(this.productServiceDB.findAll()).size() == size);
		
		size++;
		assertEquals(Lists.newArrayList(this.productServiceDB.findAll()).size(), size);
	}
	
	@Test
	public void removeTest() {
		List<Employee> productList = Lists.newArrayList(this.productServiceDB.findAll());
		when(this.productRepository.findAll()).thenReturn(productList);
		
		int num = 1;
		int size = productList.size();
		
		this.productServiceDB.remove(num);
		productList.remove(0);

		ArgumentCaptor <Integer> argumentCaptor = ArgumentCaptor.forClass(Integer.class);
		verify(this.productRepository).delete(argumentCaptor.capture());
		
		assertTrue(num == argumentCaptor.getValue());

		assertFalse(Lists.newArrayList(this.productServiceDB.findAll()).size() == size);
		
		size--;
		assertEquals(Lists.newArrayList(this.productServiceDB.findAll()).size(), size);
		
	}
	
	@Test
	public void getTest() {		
		int num = 1;
		
		Employee product1 = this.productServiceDB.get(num);
		verify(this.productRepository).findOne(num);
		
		assertTrue(product1.getId() == ProductServiceDBTest.ID);
		assertTrue(product1.getName().equals(ProductServiceDBTest.NAME));
		assertTrue(product1.getPhone().equals(ProductServiceDBTest.PHONE));
		assertTrue(product1.getImage() == ProductServiceDBTest.IMAGE);
	}

	@Test
	public void findAllTest() {		
		assertTrue(Lists.newArrayList(this.productServiceDB.findAll()).size() == 1);
		verify(this.productRepository).findAll();		
	}
}
