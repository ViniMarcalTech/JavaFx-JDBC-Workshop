package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.GradDao;
import model.entities.Grad;

public class GradService {
	
	private GradDao dao = DaoFactory.createGradDao();
	

	public List<Grad> findAll(){
		return dao.findAll();
	}
	
	public void saveOrUpdate(Grad obj) {
		if(obj.getId() == null) {
			dao.insert(obj);
		}
		else {
			dao.update(obj);
		}
	}
	
	public void remove(Grad obj) {
		dao.deleteById(obj.getId());
	}

}
