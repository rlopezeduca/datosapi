package org.palomafp.datosapi.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class DatosControllerFullContextIT {

	private static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();

	@LocalServerPort
	private int port;

	@Test
	void getDatos_fullContext_devuelveListaInicial() throws Exception {
		HttpResponse<String> response = doGet("/api/datos");
		String body = response.body();

		assertEquals(200, response.statusCode());
		assertEquals(5, countOccurrences(body, "\"codigo\":"));
		assertTrue(body.contains("\"codigo\":1"));
	}

	@Test
	void postYGetPorCodigo_fullContext_persisteDuranteLaPrueba() throws Exception {
		HttpResponse<String> postResponse = doPost("/api/datos?codigo=6&descripcion=" + encode("Elemento full context"));
		HttpResponse<String> getResponse = doGet("/api/datos?codigo=6");
		String body = getResponse.body();

		assertEquals(200, postResponse.statusCode());
		assertEquals(200, getResponse.statusCode());
		assertEquals(1, countOccurrences(body, "\"codigo\":"));
		assertTrue(body.contains("\"codigo\":6"));
		assertTrue(body.contains("\"descripcion\":\"Elemento full context\""));
	}

	private HttpResponse<String> doGet(String path) throws IOException, InterruptedException {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(baseUrl() + path))
				.GET()
				.build();

		return HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
	}

	private HttpResponse<String> doPost(String path) throws IOException, InterruptedException {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(baseUrl() + path))
				.POST(HttpRequest.BodyPublishers.noBody())
				.build();

		return HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
	}

	private String baseUrl() {
		return "http://localhost:" + port;
	}

	private String encode(String value) {
		return URLEncoder.encode(value, StandardCharsets.UTF_8);
	}

	private int countOccurrences(String value, String token) {
		int count = 0;
		int index = 0;

		while ((index = value.indexOf(token, index)) != -1) {
			count++;
			index += token.length();
		}

		return count;
	}
}
