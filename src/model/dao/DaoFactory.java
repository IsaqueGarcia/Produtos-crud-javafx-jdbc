package model.dao;

import db.DB;
import model.dao.impl.FrutasDaoJDBC;

public class DaoFactory {
		
	public static FrutasDao criarFrutasDao() { //Criando um metodo para fazer o acesso aos dados, Chamamos a nossa interface DAO e colocamos o nome do metodo.
		return new FrutasDaoJDBC(DB.getConnection()); // Aqui eu falo que a minha interface, o meu metodo dao, vai me retornar uma instanciação da minha classe de implementação com a classe de conexão como argumento..
	}
}
