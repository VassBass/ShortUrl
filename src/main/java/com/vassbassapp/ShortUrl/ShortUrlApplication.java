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
import service.UrlConverter;
import service.UrlService;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
@RestController
@RequestMapping
public class ShortUrlApplication {

	private static final UrlService service = new UrlService(new UrlRepository());

	public static void main(String[] args) {
		SpringApplication.run(ShortUrlApplication.class, args);
		printMainMessage();

		Scanner consoleIn = new Scanner(System.in);
		while (true){
			String request = consoleIn.next();
			if (request.equalsIgnoreCase("close") || request.equals("quit") || request.equalsIgnoreCase("quit")){
				consoleIn.close();
				System.exit(0);
			}else if (request.equalsIgnoreCase("help")) {
				printHelpMessage();
			}else if (request.equalsIgnoreCase("all")){
				List<UrlKeeper>list = service.getAllUrl();
				for (UrlKeeper keeper : list){
					System.out.println("-");
					System.out.println("longUrl: " + keeper.getLongUrl());
					System.out.println("shortUrl: http://localhost:8080/" + keeper.getShortUrl());
				}
			}else {
				if (UrlConverter.isUrl(request)) {
					UrlKeeper urlKeeper = service.addUrl(request, 6);
					System.out.println("Ваша короткая ссылка:");
					System.out.println("http://localhost:8080/" + urlKeeper.getShortUrl());
				}else {
					System.out.println("Ваш ввод не является URL-адресом");
				}
				printMainMessage();
			}
		}
	}

	private static void printHelpMessage(){
		System.out.println("Что б посмотреть все сохраненные ссылки введите \"all\"");
		System.out.println("Что б закончить работу приложения введите \"close\", \"quit\" или \"exit\"");
	}

	private static void printMainMessage(){
		System.out.println("Что б узнать все команды введите \"help\"");
		System.out.println("Что б преобразовать длинную ссылку в короткую введите её в консоль");
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
