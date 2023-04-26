package br.com.coldigogeladeiras.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import br.com.coldigogeladeiras.jdbcinterface.MarcaDAO;
import br.com.coldigogeladeiras.modelo.Marca;
import java.util.ArrayList;
import java.util.List;

public class JDBCMarcaDAO implements MarcaDAO {

	private Connection conexao;
	
	public JDBCMarcaDAO(Connection conexao) {
		this.conexao = conexao;
	}
	
	Marca marca = null;
	try {
		Statement stmt = conexao.createStatement();
		ResultSet rs = stmt.executeQuery(comando);
		while (rs.next()) {
			marca = new Marca();
			int id = rs.getInt("id");
			String nome = rs.getString("nome");
			
			marca.setId(id);
			marca.setNome(nome);
			
			listMarcas.add(marca);
		}

	} catch (Exception ex) {
		ex.printStackTrace();
	}
	return listMarcas;

	
}