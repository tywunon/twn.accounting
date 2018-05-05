package twn.accounting.data;

import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ListChangeListener.Change;
import twn.lang.Property;
import twn.util.Cast;

import java.io.*;

public class DataFacade {
	public static ObservableList<Transaction> transactions = FXCollections.observableArrayList();
	public static ObservableList<Account> accounts = FXCollections.observableArrayList();
	public static ObservableList<Customer> customer = FXCollections.observableArrayList();
	public static ObservableList<Provider> provider = FXCollections.observableArrayList();
		
	public static final Property<DataStore> data = new Property<>(new DataStore());
	public static final Property<Boolean> pendingChanges = new Property<>(false);
	public static final Property<String> openFilePath = new Property<>("");
	
	private static boolean syncing = false;
	private static JAXBContext jaxbContext;
	private static String dataFileName = "data.dat";
	
	static {
		try {
			jaxbContext = JAXBContext.newInstance(DataStore.class);
			
			transactions.addListener(DataFacade::listener);
			accounts.addListener(DataFacade::listener);
			customer.addListener(DataFacade::listener);
			provider.addListener(DataFacade::listener);
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	public static boolean store(String filePath) {
		try (FileOutputStream outStream = new FileOutputStream(new File(filePath), false)) {
			boolean success = store(outStream);
			if(success)
				DataFacade.openFilePath.set(filePath);
			return success;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean store(OutputStream stream) {
		try (ByteArrayOutputStream dataStream = new ByteArrayOutputStream()) {
			Marshaller m = jaxbContext.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			m.marshal(data.get(), dataStream);
			try (ZipOutputStream zip = new ZipOutputStream(stream)) {
				ZipEntry entry = new ZipEntry(dataFileName);
				zip.putNextEntry(entry);
				byte[] data = dataStream.toByteArray();
				zip.write(data, 0, data.length);
				zip.closeEntry();
				zip.close();
			}
		} catch (IOException | JAXBException e) {
			e.printStackTrace();
			return false;
		}
		pendingChanges.set(false);
		return true;
	}

	public static boolean restore(String filePath) {
		try (FileInputStream inStream = new FileInputStream(new File(filePath))) {
			boolean success = restore(inStream);
			if(success)
				DataFacade.openFilePath.set(filePath);
			return success;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean restore(InputStream stream) {
		try (ZipInputStream zip = new ZipInputStream(stream); 
			 ByteOutputStream outDataStream = new ByteOutputStream()) {
			ZipEntry entry;
			while ((entry = zip.getNextEntry()) != null) {
				if (entry.getName().equals(dataFileName)) {
					outDataStream.write(zip);
					zip.closeEntry();
					break;
				}
				zip.closeEntry();
			}
			zip.close();
			try( ByteInputStream inDataStream = outDataStream.newInputStream() ){
				Unmarshaller um = jaxbContext.createUnmarshaller();
				DataStore data = Cast.as(um.unmarshal(inDataStream), DataStore.class);
				if (data != null) {
					DataFacade.data.set(data);
					sync();
				}
			}
			
		} catch (IOException | JAXBException e) {
			e.printStackTrace();
			return false;
		}
		pendingChanges.set(false);
		return true;
	}
	
	private static void listener(Change<?> change){
		if(syncing) return;
		pendingChanges.set(true);
		if(change.getList() == transactions) {
			data.get().transactions.clear();
			data.get().transactions.addAll(transactions);
		}
		
		if(change.getList() == accounts) {
			data.get().accounts.clear();
			data.get().accounts.addAll(accounts);
		}
		
		if(change.getList() == customer) {
			data.get().customer.clear();
			data.get().customer.addAll(customer);
		}
		
		if(change.getList() == provider) {
			data.get().provider.clear();
			data.get().provider.addAll(provider);		
		}
	}
	
	public static void sync()
	{
		syncing = true;
		
		transactions.clear();
		accounts.clear();
		customer.clear();
		provider.clear();
		
		transactions.addAll(data.get().transactions);
		accounts.addAll(data.get().accounts);
		customer.addAll(data.get().customer);
		provider.addAll(data.get().provider);
		
		syncing = false;
	}
	
	public static boolean fileExists(String filePath){
		return new File(filePath).exists();
	}
}
