package com.vassbassapp.ShortUrl;

import com.vassbassapp.ShortUrl.model.UrlKeeper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.vassbassapp.ShortUrl.repository.UrlRepository;
import com.vassbassapp.ShortUrl.service.UrlConverter;
import com.vassbassapp.ShortUrl.service.UrlService;

import java.util.List;

@SpringBootApplication
@Controller
public class ShortUrlApplication {

	public static final UrlService service = new UrlService(new UrlRepository());

	public static void main(String[] args) {
		SpringApplication.run(ShortUrlApplication.class, args);

		new TerminalController().start();
	}

	@GetMapping("/")
	public String mainForm(Model model){
		model.addAttribute("keeper", new UrlKeeper());
		return "form";
	}

	@PostMapping("/")
	public String shortSubmit(@ModelAttribute UrlKeeper fromModel, Model model){
		if (UrlConverter.isUrl(fromModel.getLongUrl())) {
			UrlKeeper keeper = service.addUrl(fromModel.getLongUrl(), 6);
			model.addAttribute("keeper", keeper);
			return "result";
		}else {
			return "not-url";
		}
	}

	@GetMapping(path = "/{shortUrl}")
	public ResponseEntity<String> redirect(@PathVariable("shortUrl") String shortUrl) {
		String longUrl = service.getLongUrl("http://localhost:8080/" + shortUrl);
		if (longUrl != null) {
			HttpHeaders headers = new HttpHeaders();
			headers.add("Location", longUrl);
			return new ResponseEntity<>(headers, HttpStatus.FOUND);
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
