package impl;

public class ClientData {
	
	private String name;
	private String surname;
	private String country;
	private String city;
	private String street;
	private String housenumber;
	private String zipcode;
	private String email;

	public boolean equals(ClientData other){
		if(other.name == this.name &&
				other.surname == this.surname &&
				other.country == this.country &&
				other.city == this.city &&
				other.street == this.street &&
				other.housenumber == this.housenumber &&
				other.zipcode == this.zipcode &&
				other.email == this.email ){
			return true;
		}
		return false;
	}
	

}
