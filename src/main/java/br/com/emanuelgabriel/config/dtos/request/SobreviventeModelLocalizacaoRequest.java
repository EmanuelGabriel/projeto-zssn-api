package br.com.emanuelgabriel.config.dtos.request;

import javax.validation.constraints.NotBlank;

public class SobreviventeModelLocalizacaoRequest {

	@NotBlank(message = "Latitude não pode ser vazio")
	private String latitude;

	@NotBlank(message = "Longitude não pode ser vazio")
	private String longitude;

	public SobreviventeModelLocalizacaoRequest() {
	}

	public SobreviventeModelLocalizacaoRequest(String latitude, String longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

}
