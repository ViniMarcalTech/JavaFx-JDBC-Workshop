package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.AniversariantesDao;
import model.entities.Aniversariantes;

public class AniversariantesService {
	
	private AniversariantesDao dao = DaoFactory.createAniversariantesDao();
	

	public List<Aniversariantes> findAll(){
		return dao.findAll();
	}
	
	public void saveOrUpdate(Aniversariantes obj) {
		if(obj.getId() == null) {
			dao.insert(obj);
		}
		else {
			dao.update(obj);
		}
	}
	
	public void remove(Aniversariantes obj) {
		dao.deleteById(obj.getId());
	}

}
