package twn.accounting.data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@XmlRootElement(name = "account")
@XmlType()
public class Account {
	private final StringProperty accountNo;
	private final StringProperty description;
	private final DoubleProperty earnings;
	private final DoubleProperty expenditure;

	public Account() {
		this.accountNo = new SimpleStringProperty();
		this.description = new SimpleStringProperty();
		this.earnings = new SimpleDoubleProperty();
		this.expenditure = new SimpleDoubleProperty();
	}
	
	public Account(String accountNo, String description) {
		this(accountNo, description, 0.0, 0.0);
	}
	
	public Account(String accountNo, String description, Double earnings, Double expenditure) {
		this();
		this.accountNo.set(accountNo);
		this.description.set(description);
		this.earnings.set(earnings);
		this.expenditure.set(expenditure);
	}

	@XmlElement(name = "accountNo")
	public String getAccountNo() {
		return accountNo.get();
	}

	@XmlElement(name = "description")

	public String getDescription() {
		return description.get();
	}

	@XmlElement(name = "earnings")
	public Double getEarnings() {
		return earnings.get();
	}

	@XmlElement(name = "expenditure")
	public Double getExpenditure() {
		return expenditure.get();
	}

	public Account setAccountNo(String accountNo) {
		this.accountNo.set(accountNo);
		return this;
	}

	public Account setDescription(String description) {
		this.description.set(description);
		return this;
	}

	public Account setEarnings(Double earnings) {
		this.earnings.set(earnings);
		return this;
	}

	public Account setExpenditure(Double expenditure) {
		this.expenditure.set(expenditure);
		return this;
	}
	
	public void update(Account other){
		if(other == null) return;
		this.accountNo.set(other.accountNo.get());
		this.description.set(other.description.get());
		this.earnings.set(other.earnings.get());
		this.expenditure.set(other.expenditure.get());
	}
	
	@Override
	public String toString() {
		return getAccountNo();
	}
}
