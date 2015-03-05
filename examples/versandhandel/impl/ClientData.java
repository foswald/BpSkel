package impl;

public class ClientData {
	
	private String name;
	private String surname;
	private String country;
	private String city;
	private String street;
	private String zipcode;
	private String email;

	//0Nr. ; 1Anrede  ;  2Titel  ;  3Vorname  ;  4Nachname  ;  5Geburtsdatum  ;  6Straﬂe  ;  7PLZ  ;  8Stadt  ;  9Telefon  ;  10Mobil  ;  11Telefax  ;  12EMail ;  13Newsletter
	public ClientData(String[] data){
		assert data.length == 8;
		this.setName(data[3]);
		this.setSurname(data[4]);
		this.setCountry("Deutschland");
		this.setCity(data[8]);
		this.setStreet(data[6]);
		this.setZipcode(data[7]);
		this.setEmail(data[12]);
		
		
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}
	/**
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}
	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}
	/**
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}
	/**
	 * @return the zipcode
	 */
	public String getZipcode() {
		return zipcode;
	}
	/**
	 * @param zipcode the zipcode to set
	 */
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	public boolean equals(ClientData other){
		if(other.name.equals(this.name) &&
				other.surname.equals(this.surname) &&
				other.country.equals(this.country) &&
				other.city.equals(this.city) &&
				other.street.equals(this.street) &&
				other.zipcode.equals(this.zipcode) &&
				other.email.equals(this.email) ){
			return true;
		}
		return false;
	}
	
	public String toString(){
		String msg = String.format("%s, %s, %s, %s, %s, %s, %s ", 
				this.name, this.surname, this.country, 
				this.city, this.street, this.zipcode, this.email);
		return msg;
	}
	

}
