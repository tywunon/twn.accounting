package twn.accounting.ui;

import java.io.IOException;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.ButtonBar.ButtonData;
import twn.accounting.data.Customer;

public class CustomerEditor extends Dialog<Customer> {
	public static Customer showDialogNew()
	{
		CustomerEditor customerDialog = new CustomerEditor();
		customerDialog.setNewData();
		Optional<Customer> result = customerDialog.showAndWait();
		return result.isPresent() ? result.get() : null;
	}
	

	public static Customer showDialogEdit(Customer customer){
		if(customer == null) return null;
		CustomerEditor customerDialog = new CustomerEditor();
			customerDialog.setEditData(customer);
		Optional<Customer> result = customerDialog.showAndWait();
		return result.isPresent() ? result.get() : null;
	}
	
	private CustomerEditor() {
		this.setTitle("Kunden Editor");
		try {
			DialogPane pane = (DialogPane)FXMLLoader.load(getClass().getResource("customereditor.fxml"));
			pane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
			mapFXMLFields(pane);
			((Button)pane.lookupButton(ButtonType.OK)).setText("Anlegen");
			setResultConverter(this::converter);
			setDialogPane(pane);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private Customer converter(ButtonType button){
		
		if(button == null) return null;
		return button.getButtonData() == ButtonData.OK_DONE 
				?  new Customer(tfCustomerNo.getText(), tfName.getText(), tfStreet.getText(), tfPostalCode.getText(), tfCity.getText(), tfCountry.getText()) 
				: null;
	}
	
	private void mapFXMLFields(DialogPane pane){
	    lblCustomerNo = (Label) pane.lookup("#lblCustomerNo");
	    lblName = (Label) pane.lookup("#lblName");
	    lblStreet = (Label) pane.lookup("#lblStreet");
	    lblPostalCode = (Label) pane.lookup("#lblPostalCode");
	    lblCity = (Label) pane.lookup("#lblCity");
	    lblCountry = (Label) pane.lookup("#lblCountry");
	    tfCustomerNo = (TextField) pane.lookup("#tfCustomerNo");
	    tfName = (TextField) pane.lookup("#tfName");
	    tfStreet = (TextField) pane.lookup("#tfStreet");
	    tfPostalCode = (TextField) pane.lookup("#tfPostalCode");
	    tfCity = (TextField) pane.lookup("#tfCity");
	    tfCountry = (TextField) pane.lookup("#tfCountry");
	}
	
	private void setEditData(Customer customer){
		tfCustomerNo.setText(customer.getCustomerNo());
		tfName.setText(customer.getName());
		tfStreet.setText(customer.getStreet());
		tfPostalCode.setText(customer.getPostalCode());
		tfCity.setText(customer.getCity());
		tfCountry.setText(customer.getCountry());
	}
	
	private void setNewData(){
		tfCustomerNo.setText("");
		tfName.setText("");
		tfStreet.setText("");
		tfPostalCode.setText("");
		tfCity.setText("");
		tfCountry.setText("");
	}
	
    @FXML
    private Label lblCustomerNo;

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
    private TextField tfCustomerNo;

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
