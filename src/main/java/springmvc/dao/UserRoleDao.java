package springmvc.dao;

import java.util.List;

import springmvc.model.UserRole;

public interface UserRoleDao {

	public UserRole findById(int id);

	public UserRole findByRole(String role);

	public List<UserRole> findAllRoles();

}
