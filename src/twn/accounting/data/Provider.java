package twn.accounting.data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@XmlRootElement(name = "provider")
@XmlType()
public class Provider {
	private final StringProperty providerNo;
	private final StringProperty name;
	private final StringProperty street;
	private final StringProperty postalCode;
	private final StringProperty city;
	private final StringProperty country;

	public Provider()
	{
		providerNo = new SimpleStringProperty();
		name = new SimpleStringProperty();
		street = new SimpleStringProperty();
		postalCode = new SimpleStringProperty();
		city = new SimpleStringProperty();
		country = new SimpleStringProperty();
	}
	
	public Provider(String providerNo, String name, String street, String postalCode, String city, String country) {
		this();
		this.providerNo.set(providerNo);
		this.name.set(name);
		this.street.set(street);
		this.postalCode.set(postalCode);
		this.city.set(city);
		this.country.set(country);
	}

	@XmlElement(name = "providerNo")
	public String getProviderNo() {
		return providerNo.get();
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

	public Provider setName(String name) {
		this.name.set(name);
		return this;
	}

	public Provider setStreet(String street) {
		this.street.set(street);
		return this;
	}

	public Provider setPostalCode(String postalCode) {
		this.postalCode.set(postalCode);
		return this;
	}

	public Provider setCity(String city) {
		this.city.set(city);
		return this;
	}

	public Provider setCountry(String country) {
		this.country.set(country);
		return this;
	}
	
	public void update(Provider other){
		if(other == null) return;
		this.providerNo.set(other.providerNo.get());
		this.name.set(other.name.get());
		this.street.set(other.street.get());
		this.postalCode.set(other.postalCode.get());
		this.city.set(other.city.get());
		this.country.set(other.country.get());
	}

}
