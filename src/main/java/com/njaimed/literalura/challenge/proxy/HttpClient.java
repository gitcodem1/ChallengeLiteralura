package com.njaimed.literalura.challenge.proxy;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class HttpClient {
    public static String retrieveData(String url) {
        java.net.http.HttpClient client = java.net.http.HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException("Error al obtener datos de la API", e);
        } catch (InterruptedException e) {
            throw new RuntimeException("La solicitud fue interrumpida", e);
        }

        return response.body();
    }
}