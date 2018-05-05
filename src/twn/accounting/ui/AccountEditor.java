package twn.accounting.ui;

import java.io.IOException;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.ButtonBar.ButtonData;
import twn.accounting.data.Account;

public class AccountEditor extends Dialog<Account> {
	public static Account showDialogNew()
	{
		AccountEditor accountDialog = new AccountEditor();
		accountDialog.setNewData();
		Optional<Account> result = accountDialog.showAndWait();
		return result.isPresent() ? result.get() : null;
	}
	

	public static Account showDialogEdit(Account account) {
		if(account == null) return null;
		AccountEditor accountDialog = new AccountEditor();
			accountDialog.setEditData(account);
		Optional<Account> result = accountDialog.showAndWait();
		return result.isPresent() ? result.get() : null;
	}
	
	private AccountEditor() {
		this.setTitle("Konten Editor");
		try {
			DialogPane pane = (DialogPane)FXMLLoader.load(getClass().getResource("accounteditor.fxml"));
			pane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
			mapFXMLFields(pane);
			((Button)pane.lookupButton(ButtonType.OK)).setText("Anlegen");
			setResultConverter(this::converter);
			setDialogPane(pane);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private Account converter(ButtonType button){
		
		if(button == null) return null;
		return button.getButtonData() == ButtonData.OK_DONE 
				?  new Account(tfAcountNo.getText(), tfDescription.getText())
				: null;
	}
	
	private void mapFXMLFields(DialogPane pane){
		lblAcountNo = (Label) pane.lookup("#lblAcountNo");
		lblDescription = (Label) pane.lookup("#lblDescription");
		tfAcountNo = (TextField) pane.lookup("#tfAcountNo");
		tfDescription = (TextField) pane.lookup("#tfDescription");
	}
	
	private void setEditData(Account transaction){
		tfAcountNo.setText(transaction.getAccountNo());
		tfDescription.setText(transaction.getDescription());
	}
	
	private void setNewData(){
		tfAcountNo.setText("");
		tfDescription.setText("");
	}
	
    @FXML
    private Label lblAcountNo;

    @FXML
    private Label lblDescription;

    @FXML
    private TextField tfAcountNo;

    @FXML
    private TextField tfDescription;

}
