package springmvc.service;

import java.util.List;

import springmvc.model.UserRole;

public interface UserRoleService {

	public UserRole getUserRoleById(int id);

	public UserRole getUserRoleByRole(String role);

	public List<UserRole> getAllUserRoles();

}
