package twn.accounting.ui;

import java.io.IOException;
import java.util.Optional;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.ButtonBar.ButtonData;

public class PassDialog extends Dialog<String>{

	public static String showDialog(){
		Optional<String> result = new PassDialog().showAndWait();
		return result.isPresent() ? result.get() : null;
	}
		
	private PassDialog(){
		try {
			DialogPane pane = (DialogPane)FXMLLoader.load(getClass().getResource("passdialog.fxml"));
			pane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
			mapFXMLFields(pane);
			((Button)pane.lookupButton(ButtonType.OK)).setText("Login");
			setResultConverter(this::converter);
			setDialogPane(pane);
			
			Platform.runLater(() -> pfPassword.requestFocus());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private String converter(ButtonType button){
		
		if(button == null) return null;
		return button.getButtonData() == ButtonData.OK_DONE ?  pfPassword.getText() :null;
	}
	
	private void mapFXMLFields(DialogPane pane){
		pfPassword = (PasswordField)pane.lookup("#pfPassword");
	}
	
	@FXML private PasswordField pfPassword;
}
