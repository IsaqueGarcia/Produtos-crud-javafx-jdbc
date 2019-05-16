package model.dao;

import java.util.List;

import entities.Frutas;

public interface FrutasDao {

	public void inserirFrutas(Frutas obj);
	public void deletarFrutasPeloId(Integer id);
	public void atualizarFrutas(Frutas obj);
	List<Frutas> findAll();
	
	
}
