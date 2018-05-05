package twn.accounting.ui;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.ButtonBar.ButtonData;
import twn.accounting.data.Account;
import twn.accounting.data.Customer;
import twn.accounting.data.DataFacade;
import twn.accounting.data.Transaction;

public class TransactionEditor extends Dialog<Transaction> {
	public static Transaction showDialogNew()
	{
		TransactionEditor transactionDialog = new TransactionEditor();
		transactionDialog.setNewData();
		Optional<Transaction> result = transactionDialog.showAndWait();
		return result.isPresent() ? result.get() : null;
	}
	

	public static Transaction showDialogEdit(Transaction transaction) {
		if(transaction == null) return null;
		TransactionEditor transactionDialog = new TransactionEditor();
			transactionDialog.setEditData(transaction);
		Optional<Transaction> result = transactionDialog.showAndWait();
		return result.isPresent() ? result.get() : null;
	}
	
	private TransactionEditor() {
		this.setTitle("Buchungs Editor");
		try {
			DialogPane pane = (DialogPane)FXMLLoader.load(getClass().getResource("transactioneditor.fxml"));
			pane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
			mapFXMLFields(pane);
			((Button)pane.lookupButton(ButtonType.OK)).setText("Buchen");
			setResultConverter(this::converter);
			setDialogPane(pane);
			cbFromAccount.setItems(DataFacade.accounts);
			cbToAccount.setItems(DataFacade.accounts);
			cbCusomer.setItems(DataFacade.customer);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private Transaction converter(ButtonType button){
		
		if(button == null) return null;
		return button.getButtonData() == ButtonData.OK_DONE 
				?  new Transaction(dpTransactionDate.getValue()
						, dpReceiptDate.getValue()
						, cbCusomer.getValue()
						, cbFromAccount.getValue()
						, cbToAccount.getValue()
						, Double.parseDouble(tfAmount.getText())
						, taText.getText()) 
				: null;
	}
	
	@SuppressWarnings("unchecked")
	private void mapFXMLFields(DialogPane pane){
		lblTransactionDate = (Label) pane.lookup("#lblTransactionDate");
		lblReceiptDate = (Label) pane.lookup("#lblReceiptDate");
		lblFromAccount = (Label) pane.lookup("#lblFromAccount");
		lblToAccount = (Label) pane.lookup("#lblToAccount");
		lblAmount = (Label) pane.lookup("#lblAmount");
		dpTransactionDate = (DatePicker) pane.lookup("#dpTransactionDate");
		dpReceiptDate = (DatePicker) pane.lookup("#dpReceiptDate");
		cbToAccount = (ComboBox<Account>) pane.lookup("#cbToAccount");
		cbFromAccount = (ComboBox<Account>) pane.lookup("#cbFromAccount");
		tfAmount = (TextField) pane.lookup("#tfAmount");
		lblCustomer = (Label) pane.lookup("#lblCustomer");
		cbCusomer = (ComboBox<Customer>) pane.lookup("#cbCusomer");
		lblText = (Label) pane.lookup("#lblText");
		taText = (TextArea) pane.lookup("#taText");
	}
	
	private void setEditData(Transaction transaction){
		dpTransactionDate.setValue(transaction.getTransactionDate());
		dpReceiptDate.setValue(transaction.getReceiptDate());
		cbToAccount.setValue(transaction.getToAccount());
		cbFromAccount.setValue(transaction.getFromAccount());
		cbCusomer.setValue(transaction.getCustomer());
		tfAmount.setText(transaction.getAmount()+"");
		taText.setText(transaction.getText());
	}
	
	private void setNewData(){
		dpTransactionDate.setValue(LocalDate.now());
		dpReceiptDate.setValue(LocalDate.now());
		cbToAccount.setValue(null);
		cbFromAccount.setValue(null);
		cbCusomer.setValue(null);
		tfAmount.setText("");
		taText.setText("");
	}
	
	@FXML
	private Label lblTransactionDate;

	@FXML
	private Label lblReceiptDate;

	@FXML
	private Label lblFromAccount;

	@FXML
	private Label lblToAccount;

	@FXML
	private Label lblAmount;

	@FXML
	private DatePicker dpTransactionDate;

	@FXML
	private DatePicker dpReceiptDate;

	@FXML
	private ComboBox<Account> cbToAccount;

	@FXML
	private ComboBox<Account> cbFromAccount;

	@FXML
	private TextField tfAmount;

	@FXML
	private Label lblCustomer;

	@FXML
	private ComboBox<Customer> cbCusomer;

	@FXML
	private Label lblText;

	@FXML
	private TextArea taText;

}
