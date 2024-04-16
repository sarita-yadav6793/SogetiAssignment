package pojo;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class GetApiDetails {
	@SerializedName("country abbreviation")	private String countryAbbr;
	private List<Places> places;
	private String country;
	@SerializedName("place name") private String placeName;
	private String state;
	@SerializedName("state abbreviation") private String stateAbbr;
	
	public String getCountryAbbr() {
		return countryAbbr;
	}
	public void setCountryAbbr(String countryAbbr) {
		this.countryAbbr = countryAbbr;
	}
	public List<Places> getPlaces() {
		return places;
	}
	public void setPlaces(List<Places> places) {
		this.places = places;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPlaceName() {
		return placeName;
	}
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getStateAbbr() {
		return stateAbbr;
	}
	public void setStateAbbr(String stateAbbr) {
		this.stateAbbr = stateAbbr;
	}
	
	
	

}
