package springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import springmvc.dao.UserRoleDao;
import springmvc.model.UserRole;

@Service("userRoleService")
@Transactional
public class UserRoleServiceImpl implements UserRoleService {

	@Autowired
	UserRoleDao userRoleDao;

	public UserRole getUserRoleById(int id) {

		return userRoleDao.findById(id);

	}

	public UserRole getUserRoleByRole(String role) {

		return userRoleDao.findByRole(role);

	}

	public List<UserRole> getAllUserRoles() {

		return userRoleDao.findAllRoles();

	}

}
