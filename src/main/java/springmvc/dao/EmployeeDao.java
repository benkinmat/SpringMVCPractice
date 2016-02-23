package springmvc.dao;

import java.util.List;

import springmvc.model.Employee;

public interface EmployeeDao {

	public Employee findById(int id);

	public List<Employee> findAllEmployees();

	public Employee findEmployeeBySsn(String ssn);

	public void deleteEmployeeBySsn(String ssn);

	public void saveEmployee(Employee emp);

}
