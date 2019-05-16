package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import entities.Frutas;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.dao.DaoFactory;
import model.dao.FrutasDao;

public class FrutasListController implements Initializable, DataChangeListener {

	

	// Aqui estamos criando as associações dos nossos componentes.
	@FXML
	private TableView<Frutas> tableViewFrutas;

	@FXML
	private TableColumn<Frutas, Integer> tablecColumnId;

	@FXML
	private TableColumn<Frutas, String> tableColumnNome;

	@FXML
	private TableColumn<Frutas, Double> tableColumnPreco;

	@FXML
	private TableColumn<Frutas, Integer> tableColumnQdt;

	@FXML
	private TableColumn<Frutas, String> tableColumnFornecedor;

	@FXML
	private Button btRegistrar;

	@FXML
	private Button btRemover;
	
	@FXML
	private Button btAtualizar;
	
	
	public void onBtAtualizar(ActionEvent event) {
		Stage parentStage = Utils.currenteStage(event);
		Frutas obj = new Frutas();
		AtualizarFrutas(obj,"/gui/FrutasAtualizar.fxml" , parentStage);
	}

	//Metodo para criar uma nova janela ao pressioanr o botão.
	public void onBtRegistrar(ActionEvent event) {
		Stage parentStage = Utils.currenteStage(event);
		Frutas obj = new Frutas();
		criarDialogForm(obj,"/gui/FrutasForm.fxml", parentStage);
		
	}
	
	public void onBtRemover(ActionEvent event) {
		Stage parentStage = Utils.currenteStage(event);
		criarDialogremove("/gui/FrutasRemove.fxml", parentStage);
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		iniciarColunas();
	}

	private void iniciarColunas() {
		tablecColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tableColumnPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
		tableColumnQdt.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
		tableColumnFornecedor.setCellValueFactory(new PropertyValueFactory<>("fornecedor"));
		
		tableViewFrutas.setItems(listaDeFrutas());
	}
	
	private ObservableList<Frutas> listaDeFrutas(){
		FrutasDao frutasDao = DaoFactory.criarFrutasDao();
		List<Frutas> list = frutasDao.findAll();
		return FXCollections.observableArrayList(list);
	}
	
	//Criar a janela de formulario.
	
	private void criarDialogForm(Frutas obj,String absoluteName,Stage parentStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();
			FrutasFormController controller = loader.getController();
			controller.inscreverDataChange(this);
			
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Insira os novos dados");
			dialogStage.setScene(new Scene(pane));
			dialogStage.setResizable(false);
			dialogStage.initOwner(parentStage);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();
		}
		catch(IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}
	
	private void criarDialogremove(String absoluteName,Stage parentStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();
			FrutasRemoveController controller = loader.getController();
			controller.inscreverDataChange(this);
			
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Remova um item");
			dialogStage.setScene(new Scene(pane));
			dialogStage.setResizable(false);
			dialogStage.initOwner(parentStage);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();
		}
		catch(IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}
	private void AtualizarFrutas(Frutas obj,String absoluteName,Stage parentStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();
			FrutasAtualizarController controller = loader.getController();
			controller.inscreverDataChange(this);
			
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Atualize o seu produto");
			dialogStage.setScene(new Scene(pane));
			dialogStage.setResizable(false);
			dialogStage.initOwner(parentStage);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();
		}
		catch(IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}

	public void onDataChanged() {
		iniciarColunas();
		
	}

}
