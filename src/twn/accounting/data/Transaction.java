package twn.accounting.data;

import java.time.LocalDate;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@XmlRootElement(name = "transaction")
@XmlType()
public class Transaction {
	
	ObjectProperty<LocalDate> transactionDate;
	ObjectProperty<LocalDate> receiptDate;
	ObjectProperty<Customer> customer;
	ObjectProperty<Account> fromAccount;
	ObjectProperty<Account> toAccount;
	DoubleProperty amount;
	StringProperty text;

	public Transaction(){
		super();
		this.transactionDate =  new SimpleObjectProperty<LocalDate>();
		this.receiptDate = new SimpleObjectProperty<LocalDate>();
		this.customer = new SimpleObjectProperty<Customer>();
		this.fromAccount = new SimpleObjectProperty<Account>();
		this.toAccount = new SimpleObjectProperty<Account>();
		this.amount = new SimpleDoubleProperty();
		this.text = new SimpleStringProperty();
	}
	
	public Transaction(LocalDate transactionDate, LocalDate receiptDate, Customer customer, Account fromAccount, Account toAccount, double amount, String text) {
		this();
		this.transactionDate.set(transactionDate);
		this.receiptDate.set(receiptDate);
		this.customer.set(customer);
		this.fromAccount.set(fromAccount);
		this.toAccount.set(toAccount);
		this.amount.set(amount);
		this.text.set(text);
	}

	@XmlJavaTypeAdapter(value = Adapter.LocalDateAdapter.class)
	@XmlElement(name = "transactionDate")
	public LocalDate getTransactionDate() {
		return transactionDate.get();
	}

	@XmlJavaTypeAdapter(value = Adapter.LocalDateAdapter.class)
	@XmlElement(name = "receiptDate")
	public LocalDate getReceiptDate() {
		return receiptDate.get();
	}

	@XmlElement(name = "customer")
	public Customer getCustomer() {
		return customer.get();
	}

	@XmlElement(name = "fromAccount")
	public Account getFromAccount() {
		return fromAccount.get();
	}

	@XmlElement(name = "toAccount")
	public Account getToAccount() {
		return toAccount.get();
	}
	
	@XmlElement(name = "amount")
	public double getAmount() {
		return amount.get();
	}

	@XmlElement(name = "text")
	public String getText() {
		return text.get();
	}

	public Transaction setTransactionDate(LocalDate transactionDate) {
		this.transactionDate.set(transactionDate);
		return this;
	}

	public Transaction setReceiptDate(LocalDate receiptDate) {
		this.receiptDate.set(receiptDate);
		return this;
	}

	public Transaction setCustomer(Customer customer) {
		this.customer.set(customer);
		return this;
	}

	public Transaction setFromAccount(Account fromAccount) {
		this.fromAccount.set(fromAccount);
		return this;
	}

	public Transaction setToAccount(Account toAccount) {
		this.toAccount.set(toAccount);
		return this;
	}

	public Transaction setAmount(double amount) {
		this.amount.set(amount);
		return this;
	}


	public Transaction setText(String text) {
		this.text.set(text);
		return this;
	}
	
	public void update(Transaction other){
		if(other == null) return;
		this.transactionDate.set(other.transactionDate.get());
		this.receiptDate.set(other.receiptDate.get());
		this.customer.set(other.customer.get());
		this.fromAccount.set(other.fromAccount.get());
		this.toAccount.set(other.toAccount.get());
		this.amount.set(other.amount.get());
		this.text.set(other.text.get());
	}
	
	@Override
	public String toString() {
		return super.toString();
	}

}
