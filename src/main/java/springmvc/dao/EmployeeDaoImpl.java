package springmvc.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import springmvc.model.Employee;

@Repository("employeeDao")
public class EmployeeDaoImpl extends AbstractDao<Integer, Employee> implements
		EmployeeDao {

	@SuppressWarnings("unchecked")
	public List<Employee> findAllEmployees() {

		Criteria criteria = getCriteria();
		return (List<Employee>) criteria.list();

	}

	public Employee findEmployeeBySsn(String ssn) {

		Criteria criteria = getCriteria();
		criteria.add(Restrictions.eq("ssn", ssn));
		Employee employee = (Employee) criteria.uniqueResult();

		if (employee != null)
			Hibernate.initialize(employee.getUserRoleSet());

		return employee;

	}

	public void deleteEmployeeBySsn(String ssn) {

		delete(findEmployeeBySsn(ssn));

	}

	public void saveEmployee(Employee emp) {

		persist(emp);

	}

	public Employee findById(int id) {

		Employee employee = getbyKey(id);

		if (employee != null)
			Hibernate.initialize(employee.getUserRoleSet());

		return employee;

	}

}
