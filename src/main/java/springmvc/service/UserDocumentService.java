package springmvc.service;

import java.util.List;

import springmvc.model.UserDocument;

public interface UserDocumentService {

	public UserDocument findById(Integer id);

	public List<UserDocument> findAll();

	public void save(UserDocument userDocument);

	public void deleteById(Integer id);

}
