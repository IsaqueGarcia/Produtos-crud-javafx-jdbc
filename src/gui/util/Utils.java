package gui.util;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class Utils {

	public static Stage currenteStage(ActionEvent event) {
		return (Stage) ((Node)event.getSource()).getScene().getWindow();
	}
	
	public static Integer tryParseToInt(String str) {
		try {
		return Integer.parseInt(str);
		}
		catch(NumberFormatException e) {
			return null;
		}
	}	
	//Isso e uma class utilitaria onde voce criar alguns metodos de ajuda
	//Metodo para converter de String para double
	public static Double tryParseToDouble(String str) { //Meotdo que recebe valor double e pede como argumento uma String.
		try {
			return Double.parseDouble(str); //Converte a string para double.
		}catch(NumberFormatException e) {
			return null;
		}
	}
	
}
