package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.AniversariantesDao;
import model.entities.Aniversariantes;
import model.entities.Grad;
import model.entities.Om;

public class AniversariantesDaoJDBC implements AniversariantesDao {

	private Connection conn;
	
	public AniversariantesDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Aniversariantes obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					
					"INSERT INTO `mydb`.`pessoas`"
					+"(`id_posto_graduacao`,`id_om_destino`,`nome`,`data_nascimento`)"
					+"VALUES"
					+ "((),(),(),())",					
					Statement.RETURN_GENERATED_KEYS);
			
			st.setInt(1, obj.getGrad().getId());
			st.setInt(2, obj.getOm().getId());			
			st.setString(3, obj.getName());
			st.setDate(4, new java.sql.Date(obj.getBirthDate().getTime()));
			
			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultSet(rs);
			}
			else {
				throw new DbException("Unexpected error! No rows affected!");
			}
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void update(Aniversariantes obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					
					"UPDATE `mydb`.`pessoas`"
					+"SET `id_posto_graduacao` = ? ,`id_om_destino` =? ,`nome` = ?,`data_nascimento` = ?"
					+"WHERE `id` = ?");
									
					
			
			st.setInt(1, obj.getGrad().getId());
			st.setInt(2, obj.getOm().getId());		
			st.setString(3, obj.getName());
			st.setDate(4, new java.sql.Date(obj.getBirthDate().getTime()));
			st.setInt(5, obj.getId());
			
			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM `mydb`.`pessoas` WHERE Id = ?");
			
			st.setInt(1, id);
			
			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public Aniversariantes findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					
					
					  "select  pessoas.id, pessoas.nome, pessoas.data_nascimento,"
					+ " graduacao.posto as posto , om_destino.om as om "
					+ "from pessoas, graduacao, om_destino "
					+ "where pessoas.id = ?"

					
					);
			
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Om om = instantiateOm(rs);
				Grad grad = instantiateGrad(rs);
				Aniversariantes obj = instantiateAniversariantes(rs, om, grad);
				return obj;
			}
			return null;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

//	private Aniversariantes instantiateAniversariantes(ResultSet rs, Department dep) throws SQLException {
//		Aniversariantes obj = new Aniversariantes();
//		obj.setId(rs.getInt("Id"));
//		obj.setName(rs.getString("Name"));
//		obj.setEmail(rs.getString("Email"));
//		obj.setBaseSalary(rs.getDouble("BaseSalary"));
//		obj.setBirthDate(new java.util.Date(rs.getTimestamp("BirthDate").getTime())); 
//		obj.setDepartment(dep);
//		return obj;
//	}
//
//	private Department instantiateDepartment(ResultSet rs) throws SQLException {
//		Department dep = new Department();
//		dep.setId(rs.getInt("DepartmentId"));
//		dep.setName(rs.getString("DepName"));
//		return dep;
//	}

	private Grad instantiateGrad(ResultSet rs) throws SQLException {
		Grad grad = new Grad();
		grad.setId(rs.getInt("id_posto_graduacao"));
		grad.setName(rs.getString("posto"));
		return grad;
	}

	private Om instantiateOm(ResultSet rs) throws SQLException {
		Om om = new Om();
		om.setId(rs.getInt("id_om_destino"));
		om.setName(rs.getString("om"));
		return om;
	}
	
	private Aniversariantes instantiateAniversariantes(ResultSet rs, Om om, Grad grad) throws SQLException {
		Aniversariantes obj = new Aniversariantes();
		obj.setId(rs.getInt("id"));
		obj.setName(rs.getString("nome"));
		obj.setBirthDate(new java.util.Date(rs.getTimestamp("data_nascimento").getTime()));
		obj.setGrad(grad);
		obj.setOm(om);
		return obj;
	}

	

	
	
	@Override
	public List<Aniversariantes> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					
					"select pessoas.*, graduacao.posto as posto , om_destino.om as om "
					+"from pessoas, graduacao, om_destino "
					
//					"select  pessoas.id, pessoas.nome, pessoas.data_nascimento,"
//							+ " graduacao.posto as posto , om_destino.om as om "
//							+ "from pessoas, graduacao, om_destino "
//							+ "where pessoas.id = ?"
				    
					);

					
					
					
			
			rs = st.executeQuery();
			
			List<Aniversariantes> list = new ArrayList<>();
			Map<Integer, Om> oms = new HashMap<>();
			Map<Integer, Grad> grads = new HashMap<>();
			
			while (rs.next()) {
				
				Om om = oms.get(rs.getInt("id_om_destino"));
				Grad grad = grads.get(rs.getInt("id_posto_graduacao"));
				
				if (om == null) {		
										
					om = instantiateOm(rs);
					oms.put(rs.getInt("id_om_destino"), om);
				}
				
				if (grad == null) {		
					
					grad = instantiateGrad(rs);
					grads.put(rs.getInt("id_posto_graduacao"), grad);
				}
				
				Aniversariantes obj = instantiateAniversariantes(rs, om, grad);
				list.add(obj);
				
			}
			return list;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

//	@Override
//	public List<Aniversariantes> findByDepartment(Department department) {
//		PreparedStatement st = null;
//		ResultSet rs = null;
//		try {
//			st = conn.prepareStatement(
//					"SELECT seller.*,department.Name as DepName "
//					+ "FROM seller INNER JOIN department "
//					+ "ON seller.DepartmentId = department.Id "
//					+ "WHERE DepartmentId = ? "
//					+ "ORDER BY Name");
//			
//			st.setInt(1, department.getId());
//			
//			rs = st.executeQuery();
//			
//			List<Aniversariantes> list = new ArrayList<>();
//			Map<Integer, Department> map = new HashMap<>();
//			
//			while (rs.next()) {
//				
//				Department dep = map.get(rs.getInt("DepartmentId"));
//				
//				if (dep == null) {
//					dep = instantiateDepartment(rs);
//					map.put(rs.getInt("DepartmentId"), dep);
//				}
//				
//				Aniversariantes obj = instantiateAniversariantes(rs, dep);
//				list.add(obj);
//			}
//			return list;
//		}
//		catch (SQLException e) {
//			throw new DbException(e.getMessage());
//		}
//		finally {
//			DB.closeStatement(st);
//			DB.closeResultSet(rs);
//		}
//	}
}
