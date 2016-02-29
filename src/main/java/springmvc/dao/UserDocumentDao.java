package springmvc.dao;

import java.util.List;

import springmvc.model.UserDocument;

public interface UserDocumentDao {

	public UserDocument findById(Integer id);
	
	public List<UserDocument> findAll();
	
	public void delete(Integer id);
	
	public void save(UserDocument userDocument);
	
}
