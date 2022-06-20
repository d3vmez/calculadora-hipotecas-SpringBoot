package com.practicas.simulador_hipotecas.APIProvincias.dto;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"id", "name"})
@Generated(value = { "jsonschema2pojo" })
public class Provincia {
	
	@JsonProperty("id")
	private String id;
	@JsonProperty("nm")
	private String nm;
	
	@JsonProperty("id")
	public String getId() {
		return id;
	}
	@JsonProperty("id")
	public void setId(String id) {
		this.id = id;
	}
	@JsonProperty("nm")
	public String getNm() {
		return nm;
	}
	@JsonProperty("nm")
	public void setNm(String nm) {
		this.nm = nm;
	}
	
}
