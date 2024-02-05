package com.randomjoke.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication
public class DemoApplication {


		public static void main(String[] args) {
			SpringApplication.run(DemoApplication.class, args);
			getRandomJoke();
			System.exit(0);
		}

		public static void getRandomJoke() {
			try {
				String url = "https://official-joke-api.appspot.com/random_joke";
				RestTemplate restTemplate = new RestTemplate();
				ObjectMapper mapper = new ObjectMapper();

				String jSonFact = restTemplate.getForObject(url, String.class);
				JsonNode root = mapper.readTree(jSonFact);

				String joke = root.findValue("type").asText();
				String setup = root.findValue("setup").asText();
				String punchline = root.findValue("punchline").asText();
				String id = root.findValue("id").asText();

				System.out.println("**********Random Joke********");
				System.out.println("Joke: " + joke);
				System.out.println("Setup: " + setup);
				System.out.println("Punchline: " + punchline);
				System.out.println("Id: " + id);

			} catch (JsonProcessingException ex) {
				Logger.getLogger(DemoApplication.class.getName()).log(
						Level.SEVERE,
						null, ex);
				System.out.println("error in getRandomjoke");

			}
		}
}

