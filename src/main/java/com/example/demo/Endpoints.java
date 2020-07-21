package com.example.demo;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Endpoints {

	@GetMapping("/camelize")
	public String _1(
			@RequestParam("original") String original,
			@RequestParam(value="initialCap",defaultValue="false") String initialCap) {
		boolean nextCapital = Boolean.parseBoolean(initialCap);
		StringBuilder sb = new StringBuilder();
		for (int i=0;i<original.length();i++) {
			if (original.charAt(i)=='_') {
				nextCapital=true;
			} else 
			if (nextCapital) {
				sb.append(original.toUpperCase().charAt(i));
				nextCapital=false;
			} else {
				sb.append(original.charAt(i));
			}
		}
		return sb.toString();
	}
	
	@GetMapping("/redact")
	public String _2(
			@RequestParam("original") String original,
			@RequestParam("badWord") List<String> badword) {
		for (String s : badword) {
			StringBuilder sb = new StringBuilder();
			for (int i=0;i<s.length();i++) {
				sb.append("*");
			}
			original = original.replaceAll(s, sb.toString());
		}
		return original;
	}
	
	@PostMapping("/encode")
	public String _3(
			@RequestParam("message") String message,
			@RequestParam("key") String key) {
		StringBuilder sb = new StringBuilder();
		for (int i=0;i<message.length();i++) {
			if (message.toLowerCase().charAt(i)>='a' && message.toLowerCase().charAt(i)<='z') {
				sb.append(key.charAt(message.toLowerCase().charAt(i)-'a'));
			} else {
				sb.append(message.charAt(i));
			}
		}
		return sb.toString();
	}
	
	@PostMapping("/s/{find}/{replacement}")
	public String _4(
			@PathVariable("find") String find,
			@PathVariable("replacement") String replacement,
			@RequestBody String body) {
		return body.replaceAll(find, replacement);
	}
}
