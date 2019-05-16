package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import gui.util.Constraints;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.dao.DaoFactory;
import model.dao.FrutasDao;

public class FrutasRemoveController implements Initializable {

	private List<DataChangeListener> dataChangeListeners = new ArrayList<>();
	@FXML
	private TextField txtId;

	@FXML
	private Button btSalvar;

	@FXML
	private Button btCancelar;

	public void onBtSalvar(ActionEvent event) {
		deletarFruta();
		Utils.currenteStage(event).close();
		notifyDataChangeListeners();
	}

	public void onBtCancelar(ActionEvent event) {
		Utils.currenteStage(event).close();
	}

	public void initializeNodes() {
		Constraints.setTextFieldInteger(txtId);
	}

	public void initialize(URL arg0, ResourceBundle arg1) {
		initializeNodes();
	}

	private void deletarFruta() {
		FrutasDao dao = DaoFactory.criarFrutasDao();

		dao.deletarFrutasPeloId(Utils.tryParseToInt(txtId.getText()));

	}
	public void inscreverDataChange(DataChangeListener listener) {
		dataChangeListeners.add(listener);
	}
	
	private void notifyDataChangeListeners() {
		for(DataChangeListener listener : dataChangeListeners) {
			listener.onDataChanged();
		}
	}

}
