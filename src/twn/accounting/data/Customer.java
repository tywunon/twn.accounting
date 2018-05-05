package twn.accounting.data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@XmlRootElement(name = "customer")
@XmlType()
public class Customer {
	private final StringProperty customerNo;
	private final StringProperty name;
	private final StringProperty street;
	private final StringProperty postalCode;
	private final StringProperty city;
	private final StringProperty country;

	public Customer(){
		this.customerNo = new SimpleStringProperty();
		this.name = new SimpleStringProperty();
		this.street = new SimpleStringProperty();
		this.postalCode = new SimpleStringProperty();
		this.city = new SimpleStringProperty();
		this.country = new SimpleStringProperty();	
	}
	
	public Customer(String customerNo, String name, String street, String postalCode, String city, String country) {
		this();
		this.customerNo.set(customerNo);
		this.name.set(name);
		this.street.set(street);
		this.postalCode.set(postalCode);
		this.city.set(city);
		this.country.set(country);
	}

	@XmlElement(name = "customerNo")
	public String getCustomerNo() {
		return customerNo.get();
	}

	@XmlElement(name = "name")
	public String getName() {
		return name.get();
	}

	@XmlElement(name = "street")
	public String getStreet() {
		return street.get();
	}

	@XmlElement(name = "postalCode")
	public String getPostalCode() {
		return postalCode.get();
	}

	@XmlElement(name = "city")
	public String getCity() {
		return city.get();
	}

	@XmlElement(name = "country")
	public String getCountry() {
		return country.get();
	}

	public Customer setCustomerNo(String customerNo) {
		this.customerNo.set(customerNo);
		return this;
	}

	public Customer setName(String name) {
		this.name.set(name);
		return this;
	}

	public Customer setStreet(String street) {
		this.street.set(street);
		return this;
	}

	public Customer setPostalCode(String postalCode) {
		this.postalCode.set(postalCode);
		return this;
	}

	public Customer setCity(String city) {
		this.city.set(city);
		return this;
	}

	public Customer setCountry(String country) {
		this.country.set(country);
		return this;
	}
	
	public void update(Customer other){
		if(other == null) return;
		this.customerNo.set(other.customerNo.get());
		this.name.set(other.name.get());
		this.street.set(other.street.get());
		this.postalCode.set(other.postalCode.get());
		this.city.set(other.city.get());
		this.country.set(other.country.get());
	}
	
	@Override
	public String toString() {
		return String.format("%s, %s", getCustomerNo(), getName());
	}
}
