package springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import springmvc.dao.UserDocumentDao;
import springmvc.model.UserDocument;

@Service("userDocumentService")
@Transactional
public class UserDocumentServiceImpl implements UserDocumentService {

	@Autowired
	UserDocumentDao userDocumentDao;

	public UserDocument findById(Integer id) {

		return userDocumentDao.findById(id);

	}

	public List<UserDocument> findAll() {

		return userDocumentDao.findAll();

	}

	public void save(UserDocument userDocument) {

		userDocumentDao.save(userDocument);

	}

	public void deleteById(Integer id) {

		userDocumentDao.delete(id);

	}

}
