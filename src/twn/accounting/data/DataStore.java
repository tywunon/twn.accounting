package twn.accounting.data;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace = "twn.accounting.data")
public class DataStore {


	@XmlElementWrapper(name = "transactionList")
	@XmlElement(name = "transaction")
	public ArrayList<Transaction> transactions = new ArrayList<>();
	@XmlElementWrapper(name = "accountList")
	@XmlElement(name = "account")
	public ArrayList<Account> accounts = new ArrayList<>();
	@XmlElementWrapper(name = "customerList")
	@XmlElement(name = "customer")
	public ArrayList<Customer> customer = new ArrayList<>();
	@XmlElementWrapper(name = "providerList")
	@XmlElement(name = "provider")
	public ArrayList<Provider> provider = new ArrayList<>();
}
