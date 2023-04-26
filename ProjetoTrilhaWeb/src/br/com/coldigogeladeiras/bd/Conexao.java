package br.com.coldigogeladeiras.bd;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {

	private Connection conexao;
	
	public Connection abrirConexao() {
		try {
			String url = "jdbc:mysql://localhost/bdcoldigo?";
			String senha = "root";
			String usuario = "root";
			Class.forName("com.mysql.cj.jdbc.Driver");		
			conexao = DriverManager.getConnection(senha, url, usuario);		
		}catch(Exception e) {
			e.printStackTrace();
		}
		return conexao;
	}
	
	public void fecharConexao() {
		try {
			conexao.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
