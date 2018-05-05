package twn.accounting.ui;

import java.io.IOException;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.ButtonBar.ButtonData;
import twn.accounting.data.Provider;

public class ProviderEditor extends Dialog<Provider> {
	public static Provider showDialogNew()
	{
		ProviderEditor providerDialog = new ProviderEditor();
		providerDialog.setNewData();
		Optional<Provider> result = providerDialog.showAndWait();
		return result.isPresent() ? result.get() : null;
	}
	

	public static Provider showDialogEdit(Provider provider) {
		if(provider == null) return null;
		ProviderEditor providerDialog = new ProviderEditor();
		providerDialog.setEditData(provider);
		Optional<Provider> result = providerDialog.showAndWait();
		return result.isPresent() ? result.get() : null;
	}
	
	private ProviderEditor() {
		this.setTitle("Lieferanten Editor");
		try {
			DialogPane pane = (DialogPane)FXMLLoader.load(getClass().getResource("providereditor.fxml"));
			pane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
			mapFXMLFields(pane);
			((Button)pane.lookupButton(ButtonType.OK)).setText("Anlegen");
			setResultConverter(this::converter);
			setDialogPane(pane);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private Provider converter(ButtonType button){
		
		if(button == null) return null;
		return button.getButtonData() == ButtonData.OK_DONE 
				?  new Provider(tfProviderNo.getText(), tfName.getText(), tfStreet.getText(), tfPostalCode.getText(), tfCity.getText(), tfCountry.getText()) 
				: null;
	}
	
	private void mapFXMLFields(DialogPane pane){
	    lblProviderNo = (Label) pane.lookup("#lblProviderNo");
	    lblName = (Label) pane.lookup("#lblName");
	    lblStreet = (Label) pane.lookup("#lblStreet");
	    lblPostalCode = (Label) pane.lookup("#lblPostalCode");
	    lblCity = (Label) pane.lookup("#lblCity");
	    lblCountry = (Label) pane.lookup("#lblCountry");
	    tfProviderNo = (TextField) pane.lookup("#tfProviderNo");
	    tfName = (TextField) pane.lookup("#tfName");
	    tfStreet = (TextField) pane.lookup("#tfStreet");
	    tfPostalCode = (TextField) pane.lookup("#tfPostalCode");
	    tfCity = (TextField) pane.lookup("#tfCity");
	    tfCountry = (TextField) pane.lookup("#tfCountry");
	}
	
	private void setEditData(Provider provider){
		tfProviderNo.setText(provider.getProviderNo());
		tfName.setText(provider.getName());
		tfStreet.setText(provider.getStreet());
		tfPostalCode.setText(provider.getPostalCode());
		tfCity.setText(provider.getCity());
		tfCountry.setText(provider.getCountry());
	}
	
	private void setNewData(){
		tfProviderNo.setText("");
		tfName.setText("");
		tfStreet.setText("");
		tfPostalCode.setText("");
		tfCity.setText("");
		tfCountry.setText("");
	}
	
    @FXML
    private Label lblProviderNo;

    @FXML
    private Label lblName;

    @FXML
    private Label lblStreet;

    @FXML
    private Label lblPostalCode;

    @FXML
    private Label lblCity;

    @FXML
    private Label lblCountry;

    @FXML
    private TextField tfProviderNo;

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfStreet;

    @FXML
    private TextField tfPostalCode;

    @FXML
    private TextField tfCity;

    @FXML
    private TextField tfCountry;


}
