package springmvc.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import springmvc.model.UserRole;

@Repository("userRoleDao")
public class UserRoleDaoImpl extends AbstractDao<Integer, UserRole> implements
		UserRoleDao {

	public UserRole findById(int id) {

		return getbyKey(id);

	}

	public UserRole findByRole(String role) {

		Criteria criteria = getCriteria();
		criteria.add(Restrictions.eq("role", role));
		return (UserRole) criteria.uniqueResult();

	}

	@SuppressWarnings("unchecked")
	public List<UserRole> findAllRoles() {

		Criteria criteria = getCriteria();
		return (List<UserRole>) criteria.list();

	}

}
