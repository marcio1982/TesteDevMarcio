/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marcio.dao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author adm
 */
public class BaseDao {
    public Connection getConexao() {
		Connection conexao = null;
		String usuario = "postgres";
		String senha = "senha1";
		String nomeBancoDados = "teste";
 
		try {
			Class.forName("org.postgresql.Driver");
			conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5433/" + nomeBancoDados,
					 usuario, senha);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conexao;
	}
}
