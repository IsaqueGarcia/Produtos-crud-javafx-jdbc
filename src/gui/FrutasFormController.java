package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import entities.Frutas;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.dao.DaoFactory;
import model.dao.FrutasDao;

public class FrutasFormController implements Initializable {

	//Criamos uma lista de objetos dataChangeListener.
	private List<DataChangeListener> dataChangeListeners = new ArrayList<>();
	
	@FXML
	private TextField txtId;

	@FXML
	private TextField txtNome;

	@FXML
	private TextField txtPreco;

	@FXML
	private TextField txtQtd;

	@FXML
	private TextField txtForn;

	@FXML
	private Button btSalvar;

	@FXML
	private Button btCancelar;
	
	//Metodo responsavel por escrever na lista.
	public void inscreverDataChange(DataChangeListener listener) {
		dataChangeListeners.add(listener);
	}

	@FXML
	public void onBtSalvarAcao(ActionEvent event) {
		NovaFruta();
		Utils.currenteStage(event).close();
		notifyDataChangeListeners();
	}

	private void notifyDataChangeListeners() {
		for(DataChangeListener listener : dataChangeListeners) {
			listener.onDataChanged();
		}
		
	}

	@FXML
	public void onBtCancelarAcao(ActionEvent event) {
		Utils.currenteStage(event).close();

	}
	

	public void initialize(URL url, ResourceBundle rb) { //Metodo que modifica os nossos objetos em tempo de execução.
		initializeNodes();
	}

	public void initializeNodes() {  //Metodo para controlar as nossas caixas de texto, limitar caractere etc.
		Constraints.setTextFieldInteger(txtId);
		Constraints.setTextFieldMaxLength(txtNome, 30);
		Constraints.setTextFieldDouble(txtPreco);
		Constraints.setTextFieldInteger(txtQtd);
		Constraints.setTextFieldMaxLength(txtForn, 30);

	}
	
	private void NovaFruta() { //Metodo para inserir uma nova fruta.
		Frutas obj = new Frutas(); //Instanciamos um construtor vazio.
		FrutasDao dao = DaoFactory.criarFrutasDao(); //Iniciamos a conexão com o nosso banco de dados.
		
		//Passamos os valores do nosso obj Frutas, dizendo que os valores seriam pegos da nossa caixa de texto.
		//Utilizamos a classe Utils para auxiliar nas conversões de valores.
		obj.setNome(txtNome.getText());
		obj.setId(Utils.tryParseToInt(txtId.getText()));
		obj.setPreco(Utils.tryParseToDouble(txtPreco.getText()));
		obj.setQuantidade(Utils.tryParseToInt(txtQtd.getText()));
		obj.setFornecedor(txtForn.getText());
		
		//Finalizamos passando nosso obj como argumento para dao que ira rodar o nosso codigo SQL e criar uma nova fruta.
		dao.inserirFrutas(obj);
		
	}
	

}
