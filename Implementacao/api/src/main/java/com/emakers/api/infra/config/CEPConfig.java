package com.emakers.api.infra.config;

import com.emakers.api.data.dto.response.EnderecoResponseDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CEPConfig {
    public EnderecoResponseDto getEndereco(String cep) {
        EnderecoResponseDto endereco = null;
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://viacep.com.br/ws/" + cep + "/json/"))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            ObjectMapper mapper = new ObjectMapper();
            endereco = mapper.readValue(response.body(), EnderecoResponseDto.class);
        } catch (Exception e) {
            System.out.println("Erro ao buscar o endereço: " + e.getMessage());
        }
        return endereco;
    }
}
