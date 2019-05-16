package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import entities.Frutas;
import model.dao.FrutasDao;

public class FrutasDaoJDBC implements FrutasDao { //Implementar a minha interface responsavel por conter as funções que vou atribuir a classe.

	private Connection conn; //Declarei uma variavel do tipo Connection, Essa connection significa que irei trabalhar atraves de codigos SQL.

	public FrutasDaoJDBC(Connection conn) {  //Criei um construtor passando como argumento a minha variavel connection.
		this.conn = conn; //e indidiquei que a conexão que instanciei na classe sera essa msm conexão do argumento.
	}

	@Override
	public void inserirFrutas(Frutas obj) { //Função criada pela minha interface FrutasDAO.
		PreparedStatement st = null;  //Preparara os parametros para serem inseridos, inicia com valor nulo.

		try { //Aqui vc abre o seu try para tentar fzr a leitura, isso pode resultar em algum erro que vai ser capturado pelo seu catch.
			st = conn.prepareStatement( //Aqui vc inicia a sua conexão.
					"INSERT INTO frutas " + "(nome, preco, quantidade, fornecedor) " + "VALUES " + "(?,?,?,?) ");
			
			//Atribundo os valores e definindo a função.
			//Passei como argumento um objeto da entidade frutas.
			
			st.setString(1, obj.getNome()); //Aqui é onde vc passa os valores para o seu "?", seguindo a ordem em que foram pedidos.
			st.setDouble(2, obj.getPreco());
			st.setInt(3, obj.getQuantidade());
			st.setString(4, obj.getFornecedor());

			st.executeUpdate(); //Quando vc terminar de atribuir os seus valores, vc precisa executar uma atualização dos valores.

		} catch (SQLException e) { //Aqui vc faz o tratamento da sua exceção falando que pode dar um erro no seu codigo SQL.
			throw new DbException(e.getMessage()); //Aqui vc chama a classe de tratamento que vc criou.
		} finally { //Bloco para finalizar a sua conexão e os tratamentos de dados do metodo.
			DB.closeStatement(st);
		}

	}

	@Override
	public void deletarFrutasPeloId(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM frutas " + "Where id = ? ");
			st.setInt(1, id);

			st.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void atualizarFrutas(Frutas obj) {
		PreparedStatement st = null;

		try {
			st = conn.prepareStatement(
					"Update frutas " + "SET nome = ?,preco = ?,quantidade = ?,fornecedor = ? " + "where id = ? ");
			st.setString(1, obj.getNome());
			st.setDouble(2, obj.getPreco());
			st.setInt(3, obj.getQuantidade());
			st.setString(4, obj.getFornecedor());
			st.setInt(5, obj.getId());
			
			st.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
		}

	}


	@Override
	public List<Frutas> findAll() {
	PreparedStatement st = null;
	ResultSet rs = null; //ResultSet serve para mostrar os nossos dados em forma de tabela.
	try {
		st = conn.prepareStatement(
				"SELECT * FROM frutas ORDER BY nome" );
		rs = st.executeQuery();
		
		List<Frutas> list = new ArrayList<>();
		
		while(rs.next()) {
			Frutas obj = new Frutas();
			obj.setId(rs.getInt("id"));
			obj.setNome(rs.getString("nome"));
			obj.setPreco(rs.getDouble("preco"));
			obj.setQuantidade(rs.getInt("quantidade"));
			obj.setFornecedor(rs.getString("fornecedor"));
			list.add(obj);
		}
		return list;
	}
	catch(SQLException e) {
		throw new DbException(e.getMessage());
	}
	finally {
		DB.closeStatement(st);
		DB.closeResultSet(rs);
	}
	}

}
