package model.dao;

import db.DB;
import model.dao.impl.AniversariantesDaoJDBC;
import model.dao.impl.GradDaoJDBC;
import model.dao.impl.OmDaoJDBC;

public class DaoFactory {

		
	public static AniversariantesDaoJDBC createAniversariantesDao()   {		
			return new AniversariantesDaoJDBC(DB.getConnection());
	}
	
	public static GradDao createGradDao() {
		return new GradDaoJDBC(DB.getConnection());
	}
	
	public static OmDao createOmDao() {
		return new OmDaoJDBC(DB.getConnection());
	}

}
