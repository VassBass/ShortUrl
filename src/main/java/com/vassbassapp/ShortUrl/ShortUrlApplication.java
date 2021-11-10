package com.vassbassapp.ShortUrl;

import model.UrlKeeper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repository.UrlRepository;
import service.UrlService;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping
public class ShortUrlApplication {

	private static final UrlService service = new UrlService(new UrlRepository());

	public static void main(String[] args) {
		SpringApplication.run(ShortUrlApplication.class, args);

		new ConsoleApplication(service).start();
	}

	@GetMapping(path = "/")
	public String emptyRequest(){
		return "Что б просмотреть все сохраненные ссылки введите в адресную строку http://localhost:8080/all";
	}

	@GetMapping(path = "/{shortUrl}")
	public ResponseEntity redirect(@PathVariable("shortUrl") String shortUrl) {
		String longUrl = service.getLongUrl(shortUrl);
		if (longUrl != null) {
			HttpHeaders headers = new HttpHeaders();
			headers.add("Location", longUrl);
			return new ResponseEntity<String>(headers, HttpStatus.FOUND);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	List<UrlKeeper> getAllUrl(){
		return service.getAllUrl();
	}
}
