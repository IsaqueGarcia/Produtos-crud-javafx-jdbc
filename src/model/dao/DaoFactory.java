package model.dao;

import db.DB;
import model.dao.impl.FrutasDaoJDBC;

public class DaoFactory {
		
	public static FrutasDao criarFrutasDao() { //Criando um metodo para fazer o acesso aos dados, Chamamos a nossa interface DAO e colocamos o nome do metodo.
		return new FrutasDaoJDBC(DB.getConnection()); // Aqui eu falo que a minha interface, o meu metodo dao, vai me retornar uma instancia��o da minha classe de implementa��o com a classe de conex�o como argumento..
	}
}
