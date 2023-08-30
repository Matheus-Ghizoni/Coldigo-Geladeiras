package br.com.coldigogeladeiras.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.coldigogeladeiras.jdbcinterface.MarcaDAO;
import br.com.coldigogeladeiras.modelo.Marca;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonObject;

public class JDBCMarcaDAO implements MarcaDAO {

	private Connection conexao;
	
	public JDBCMarcaDAO(Connection conexao) {
		this.conexao = conexao;
	}
	
	public List<Marca> buscar(){

		String comando = "SELECT * FROM marcas WHERE status = 1";
		List<Marca> listMarcas = new ArrayList<Marca>();
		Marca marca = null;
		try {
			Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			while (rs.next()) {
				marca = new Marca();
				int id = rs.getInt("id");
				String nome = rs.getString("nome");
				int status = rs.getInt("status");
				
				marca.setId(id);
				marca.setNome(nome);
				marca.setStatus(status);
				
				listMarcas.add(marca);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return listMarcas;
		
	}
	
	public boolean inserir(Marca marca) {
	
		String comando = "INSERT INTO marcas " + "(nome) "
				+ "VALUES (?)";
		PreparedStatement p;

		try {

			p = this.conexao.prepareStatement(comando);

			p.setString(1, marca.getNome());
			
			p.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}
	
	public List<JsonObject> buscarPorNome(String nome) {
		String comando = "SELECT * FROM marcas";
		System.out.println(nome);
		if (!nome.equals("")) {

			comando += " WHERE nome LIKE '%" + nome + "%'";

		}

		List<JsonObject> listaMarcas = new ArrayList<JsonObject>();
		JsonObject marca = null;

		try {
			Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);

			while (rs.next()) {

				int id = rs.getInt("id");
				String name = rs.getString("nome");
				int status = rs.getInt("status");

				marca = new JsonObject();
				marca.addProperty("id", id);
				marca.addProperty("nome", name);
				marca.addProperty("status", status);

				listaMarcas.add(marca);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return listaMarcas;
	}
	
	public boolean deletar(int id) {
		String comando = "DELETE FROM marcas WHERE id = ?";
		PreparedStatement p;
		try {
			p = this.conexao.prepareStatement(comando);
			p.setInt(1, id);
			p.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean buscarPorId(int id) {
		String comando = "SELECT * FROM marcas WHERE id = ?";
		Marca marca = new Marca();
		try {
			PreparedStatement p = this.conexao.prepareStatement(comando);
			p.setInt(1, id);
			ResultSet rs = p.executeQuery();
			while (rs.next()) {
				marca.setId(id);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
		
	}
	
	public boolean inativar(int id) {
		
		String comando = "UPDATE marcas SET status = CASE WHEN status = 0 THEN 1 ELSE 0 END where id = ?";
				
		PreparedStatement p;

		try {

			p = this.conexao.prepareStatement(comando);

			p.setInt(1, id);
			
			p.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}
	
	public int buscaStatus(int id) {
		String comando = "SELECT * FROM marcas where id = ?";
		int status = 3;
		PreparedStatement p;

		try {

			p = this.conexao.prepareStatement(comando);
			p.setInt(1, id);
			p.execute();
			
			Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			status = rs.getInt("status");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return status;
	}
	
	public boolean confereMarca(String nome) {
		String comando = "SELECT * FROM marcas WHERE nome = '"+nome+"'";
		try {
			PreparedStatement p = this.conexao.prepareStatement(comando);
			ResultSet rs = p.executeQuery();
			while (rs.next()) {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return true;
		}

		return true;
	}
}

