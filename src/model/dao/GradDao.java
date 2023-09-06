package model.dao;

import java.util.List;

import model.entities.Grad;

public interface GradDao {

	void insert(Grad obj);
	void update(Grad obj);
	void deleteById(Integer id);
	Grad findById(Integer id);
	List<Grad> findAll();
}
