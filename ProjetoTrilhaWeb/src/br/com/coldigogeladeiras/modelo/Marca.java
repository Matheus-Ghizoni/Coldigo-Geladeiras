package br.com.coldigogeladeiras.modelo;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Marca implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String nome;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}

public List<Marca> buscar() {

	String comando = "SELECT * FROM marcas";
	List<Marca> listMarcas = new ArrayList<Marca>();
	Marca marca = null;
	return listMarcas;
	
}







