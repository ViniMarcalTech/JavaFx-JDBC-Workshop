package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.OmDao;
import model.entities.Om;

public class OmService {
	
	private OmDao dao = DaoFactory.createOmDao();
	

	public List<Om> findAll(){
		return dao.findAll();
	}
	
	public void saveOrUpdate(Om obj) {
		if(obj.getId() == null) {
			dao.insert(obj);
		}
		else {
			dao.update(obj);
		}
	}
	
	public void remove(Om obj) {
		dao.deleteById(obj.getId());
	}

}
