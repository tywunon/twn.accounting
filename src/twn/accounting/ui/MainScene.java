package twn.accounting.ui;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.*;
import javafx.stage.FileChooser.ExtensionFilter;
import twn.accounting.res.*;
import twn.accounting.data.Account;
import twn.accounting.data.Customer;
import twn.accounting.data.DataFacade;
import twn.accounting.data.Provider;
import twn.accounting.data.Transaction;
import twn.evt.EventArgs;
import twn.ui.fx.context.ContextManager;

public class MainScene {

	private static final String stateTitleValue = "TWN accounting";
	public static StringProperty stageTitle;

	static {
		stageTitle = new SimpleStringProperty(stateTitleValue);
	}

	public static void Show(Stage stage) {
		try {
			Scene scene = new Scene(FXMLLoader.load(MainScene.class.getResource("mainscene.fxml")));
			scene.getStylesheets().add(MainScene.class.getResource("application.css").toExternalForm());
			stage.setScene(scene);
			stage.getIcons().add(new Image(ResourceLoader.tywunon_trans_png()));
			stage.titleProperty().bindBidirectional(stageTitle);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private FileChooser fileDialog;

	public BooleanProperty pendingChanges;
	public StringProperty openFilePath;

    @FXML
    private Tab tabTransactions;

    @FXML
    private TableView<Transaction> tvTransactions;

    @FXML
    private Button btnNewTransaction;

    @FXML
    private Button btnEditTransaction;

    @FXML
    private Button btnDeleteTransaction;

    @FXML
    private Tab tabAccounts;

    @FXML
    private TableView<Account> tvAccounts;

    @FXML
    private Button btnNewAccount;

    @FXML
    private Button btnEditAccount;

    @FXML
    private Button btnDeleteAccount;

    @FXML
    private Button btnRestoreAccountPlan;

    @FXML
    private Tab tabCustomer;

    @FXML
    private TableView<Customer> tvCustomers;

    @FXML
    private Button btnNewCustomer;

    @FXML
    private Button btnEditCustomer;

    @FXML
    private Button btnDeleteCustomer;

    @FXML
    private Tab tabProvider;

    @FXML
    private TableView<Provider> tvProvider;

    @FXML
    private Button btnNewProvider;

    @FXML
    private Button btnEditProvider;

    @FXML
    private Button btnDeleteProvider;

    @FXML
    private MenuItem miOpen;

    @FXML
    private MenuItem miSave;

    @FXML
    private MenuItem miSaveAs;

    @FXML
    private MenuItem miClose;


	public MainScene() throws Exception {
		fileDialog = new FileChooser();
		fileDialog.setTitle("Datei auswählen");
		fileDialog.getExtensionFilters().add(new ExtensionFilter("TWN accounting Datei", "*.twnacc"));

		pendingChanges = new SimpleBooleanProperty(DataFacade.pendingChanges.get());
		openFilePath = new SimpleStringProperty(DataFacade.openFilePath.get());

		stageTitle.bind(Bindings.createStringBinding(
				() -> String.format("%s %s%s", stateTitleValue, openFilePath.get(), pendingChanges.get() ? "*" : ""),
				openFilePath, pendingChanges));
	}

	@FXML
	private void initialize() {
		DataFacade.data.valueChanged.subscribe(this::handleDataChanged);
		DataFacade.pendingChanges.valueChanged.subscribe(this::handlePendingChangesChanged);
		DataFacade.openFilePath.valueChanged.subscribe(this::handleOpenFilePathChanged);
		initControls();
		assignData();
	}
	
	private void initControls() {
		miOpen.setGraphic(createImageViewForStream(ResourceLoader.icons8_ordner_öffnen(), 15, 15));
		miSave.setGraphic(createImageViewForStream(ResourceLoader.icons8_speichern(), 15, 15));
		miSaveAs.setGraphic(createImageViewForStream(ResourceLoader.icons8_speichern_als(), 15, 15));
		miClose.setGraphic(createImageViewForStream(ResourceLoader.icons8_fenster_schließen(), 15, 15));
		
		tabTransactions.setGraphic(createImageViewForStream(ResourceLoader.icons8_bestellung(), 15, 15));
		btnNewTransaction.setGraphic(createImageViewForStream(ResourceLoader.icons8_neu_erstellen(), 15, 15));
		btnEditTransaction.setGraphic(createImageViewForStream(ResourceLoader.icons8_bearbeiten(), 15, 15));
		btnDeleteTransaction.setGraphic(createImageViewForStream(ResourceLoader.icons8_müll(), 15, 15));

		tabAccounts.setGraphic(createImageViewForStream(ResourceLoader.icons8_kontobuch(), 15, 15));
		btnNewAccount.setGraphic(createImageViewForStream(ResourceLoader.icons8_neu_erstellen(), 15, 15));
		btnEditAccount.setGraphic(createImageViewForStream(ResourceLoader.icons8_bearbeiten(), 15, 15));
		btnRestoreAccountPlan.setGraphic(createImageViewForStream(ResourceLoader.icons8_vergangenheit(), 15, 15));
		btnDeleteAccount.setGraphic(createImageViewForStream(ResourceLoader.icons8_müll(), 15, 15));
		
		tabCustomer.setGraphic(createImageViewForStream(ResourceLoader.icons8_webcam_mann(), 15, 15));
		btnNewCustomer.setGraphic(createImageViewForStream(ResourceLoader.icons8_neu_erstellen(), 15, 15));
		btnEditCustomer.setGraphic(createImageViewForStream(ResourceLoader.icons8_bearbeiten(), 15, 15));
		btnDeleteCustomer.setGraphic(createImageViewForStream(ResourceLoader.icons8_müll(), 15, 15));
		
		tabProvider.setGraphic(createImageViewForStream(ResourceLoader.icons8_lkw(), 15, 15));
		btnNewProvider.setGraphic(createImageViewForStream(ResourceLoader.icons8_neu_erstellen(), 15, 15));
		btnEditProvider.setGraphic(createImageViewForStream(ResourceLoader.icons8_bearbeiten(), 15, 15));
		btnDeleteProvider.setGraphic(createImageViewForStream(ResourceLoader.icons8_müll(), 15, 15));
		
		ContextManager.AddContextParentMenu(tvAccounts, "menu_parent", "Parent");
		ContextManager.AddContextMenu(tvAccounts, "menu_parent", "test", "Test", () -> new Alert(AlertType.INFORMATION).show(), () -> true);
	}

	private void assignData() {
		tvTransactions.setItems(DataFacade.transactions);
		tvAccounts.setItems(DataFacade.accounts);
		tvCustomers.setItems(DataFacade.customer);
		tvProvider.setItems(DataFacade.provider);
	}
	
	private ImageView createImageViewForStream(InputStream stream, int width, int height){
		ImageView view = new ImageView(new Image(stream));
		view.setFitWidth(width);
		view.setFitHeight(height);
		return view;
	}

	private void handleDataChanged(Object sender, EventArgs args) {
		assignData();
	}

	private void handlePendingChangesChanged(Object sender, EventArgs args) {
		pendingChanges.set(DataFacade.pendingChanges.get());
	}

	private void handleOpenFilePathChanged(Object sender, EventArgs args) {
		openFilePath.set(DataFacade.openFilePath.get());
	}

	@FXML
	void handleFileOpen(ActionEvent event) {
		if (pendingChanges.get()) {
			Alert msgBox = new Alert(AlertType.WARNING, "Vor dem öffnen Speichern?", ButtonType.YES, ButtonType.NO);
			Optional<ButtonType> result = msgBox.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.YES)
				handleFileSave(event);
		}
		File file = fileDialog.showOpenDialog(null);
		if (file == null)
			return;
		if (!DataFacade.restore(file.getPath())) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("Datei konnte nicht geöffnet werden");
			alert.showAndWait();
		}
	}

	@FXML
	void handleFileSave(ActionEvent event) {
		if (DataFacade.fileExists(DataFacade.openFilePath.get()))
			DataFacade.store(DataFacade.openFilePath.get());
		else
			handleFileSaveAs(event);
	}

	@FXML
	void handleFileSaveAs(ActionEvent event) {
		File file = fileDialog.showSaveDialog(null);
		if (file == null)
			return;
		DataFacade.store(file.getPath());
	}

	@FXML
	void handleFileClose(ActionEvent event) {
		Platform.exit();
	}

	@FXML
	private void handleNewTransaction(ActionEvent event) {
		Transaction newItem = TransactionEditor.showDialogNew();
		DataFacade.transactions.add(newItem);
	}

	@FXML
	private void handleEditTransaction(ActionEvent event) {
		Transaction selectedItem = tvTransactions.getSelectionModel().getSelectedItem();
		if(selectedItem == null) return;
		Transaction editItem = TransactionEditor.showDialogEdit(selectedItem);
		selectedItem.update(editItem);
		tvTransactions.refresh();
	}

	@FXML
	private void handleDeleteTransaction(ActionEvent event) {
		DataFacade.transactions.remove(tvTransactions.getSelectionModel().getSelectedItem());
	}

	@FXML
	private void handleNewAccount(ActionEvent event) {
		Account newItem = AccountEditor.showDialogNew();
		DataFacade.accounts.add(newItem);
	}

	@FXML
	private void handleEditAccount(ActionEvent event) {
		Account selectedItem = tvAccounts.getSelectionModel().getSelectedItem();
		if(selectedItem == null) return;
		Account editItem = AccountEditor.showDialogEdit(selectedItem);
		selectedItem.update(editItem);
		tvTransactions.refresh();
	}

	@FXML
	private void handleDeleteAccount(ActionEvent event) {
		DataFacade.accounts.remove(tvAccounts.getSelectionModel().getSelectedItem());
	}

	@FXML
	private void handleRestoreAccountPlan(ActionEvent event) {
	}

	@FXML
	private void handleNewCustomer(ActionEvent event) {
		Customer newItem = CustomerEditor.showDialogNew();
		DataFacade.customer.add(newItem);
	}

	@FXML
	private void handleEditCustomer(ActionEvent event) {
		Customer selectedItem = tvCustomers.getSelectionModel().getSelectedItem();
		if(selectedItem == null) return;
		Customer editItem = CustomerEditor.showDialogEdit(selectedItem);
		selectedItem.update(editItem);
		tvTransactions.refresh();
	}

	@FXML
	private void handleDeleteCustomer(ActionEvent event) {
		DataFacade.customer.remove(tvCustomers.getSelectionModel().getSelectedItem());
	}

	@FXML
	private void handleNewProvider(ActionEvent event) {
		Provider newItem = ProviderEditor.showDialogNew();
		DataFacade.provider.add(newItem);
	}

	@FXML
	private void handleEditProvider(ActionEvent event) {
		Provider selectedItem = tvProvider.getSelectionModel().getSelectedItem();
		if(selectedItem == null) return;
		Provider editItem = ProviderEditor.showDialogEdit(selectedItem);
		selectedItem.update(editItem);
		tvTransactions.refresh();
	}

	@FXML
	private void handleDeleteProvider(ActionEvent event) {
		DataFacade.provider.remove(tvProvider.getSelectionModel().getSelectedItem());
	}

}
