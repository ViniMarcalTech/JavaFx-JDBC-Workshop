package model.dao;

import java.util.List;

import model.entities.Aniversariantes;

public interface AniversariantesDao {

	void insert(Aniversariantes obj);
	void update(Aniversariantes obj);
	void deleteById(Integer id);
	Aniversariantes findById(Integer id);
	List<Aniversariantes> findAll();
}
