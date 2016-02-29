package springmvc.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import springmvc.model.UserDocument;

@Repository("userDocumentDao")
public class UserDocumentDaoImpl extends AbstractDao<Integer, UserDocument> implements UserDocumentDao{

	public UserDocument findById(Integer id) {

		return getbyKey(id);
		
	}

	@SuppressWarnings("unchecked")
	public List<UserDocument> findAll() {
		
		Criteria criteria = getCriteria();
		return (List<UserDocument>) criteria.list();
		
	}

	public void delete(Integer id) {

		delete(getbyKey(id));
		
	}

	public void save(UserDocument userDocument) {

		persist(userDocument);
		
	}	
	
}
