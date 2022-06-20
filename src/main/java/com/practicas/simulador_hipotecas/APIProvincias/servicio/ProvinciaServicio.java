package com.practicas.simulador_hipotecas.APIProvincias.servicio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.stereotype.Service;

@Service
public class ProvinciaServicio {
	
	public String leerUrl(String sUrl) {
		
		String salida = "";
		
		try {
			URL url = new URL(sUrl);
			HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			
			if(connection.getResponseCode() != 200) {
				throw new RuntimeException("Fallo: HTTP error código: " + connection.getResponseCode());
			}
			
			//Leer JSON
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuilder sb = new StringBuilder();
			//código postal
			int cp;
			while((cp = br.read()) != -1) {
				sb.append((char)cp);
			}
			
			System.out.println(sb);
			salida = sb.toString();
			connection.disconnect();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return salida;
		
	}

}
