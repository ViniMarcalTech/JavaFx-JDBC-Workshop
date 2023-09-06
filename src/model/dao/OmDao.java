package model.dao;

import java.util.List;

import model.entities.Om;

public interface OmDao {

	void insert(Om obj);
	void update(Om obj);
	void deleteById(Integer id);
	Om findById(Integer id);
	List<Om> findAll();
}
