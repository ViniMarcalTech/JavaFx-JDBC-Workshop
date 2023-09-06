package model.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Aniversariantes implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private Date birthDate;
	
	
	private Om om;
	private Grad grad;
	
	public Aniversariantes() {}
	
	public Aniversariantes(Integer id, String name, Om om, Grad grad) {
		super();
		this.id = id;
		this.name = name;
		this.om = om;
		this.grad = grad;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Om getOm() {
		return om;
	}

	public void setOm(Om om) {
		this.om = om;
	}

	public Grad getGrad() {
		return grad;
	}

	public void setGrad(Grad grad) {
		this.grad = grad;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aniversariantes other = (Aniversariantes) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "aniversariantes [id=" + id + ", name=" + name + ", om=" + om + ", grad=" + grad + "]"+"[Data="+this.birthDate+"]";
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	
	
	
}
