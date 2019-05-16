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

public class FrutasAtualizarController implements Initializable {

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

	public void inscreverDataChange(DataChangeListener listener) {
		dataChangeListeners.add(listener);
	}

	private void notifyData() {
		for (DataChangeListener listener : dataChangeListeners) {
			listener.onDataChanged();
		}
	}

	@FXML
	public void onBtSalvar(ActionEvent e) {
		AtualizarFrutas();
		Utils.currenteStage(e).close();
		notifyData();
	}

	@FXML
	public void onBtCancelar(ActionEvent e) {
		Utils.currenteStage(e).close();
	}

	public void AtualizarFrutas() {
		FrutasDao dao = DaoFactory.criarFrutasDao();
		Frutas frutas = new Frutas();

		frutas.setNome(txtNome.getText());
		frutas.setId(Utils.tryParseToInt(txtId.getText()));
		frutas.setPreco(Utils.tryParseToDouble(txtPreco.getText()));
		frutas.setQuantidade(Utils.tryParseToInt(txtQtd.getText()));
		frutas.setFornecedor(txtForn.getText());

		dao.atualizarFrutas(frutas);
	}

	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();

	}

	public void initializeNodes() {
		Constraints.setTextFieldInteger(txtId);
		Constraints.setTextFieldMaxLength(txtNome, 30);
		Constraints.setTextFieldDouble(txtPreco);
		Constraints.setTextFieldInteger(txtQtd);
		Constraints.setTextFieldMaxLength(txtForn, 30);
	}

}
