package com.aeronube.supra.AeronubeTest.web;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aeronube.supra.AeronubeTest.dto.BookDTO;
import com.aeronube.supra.AeronubeTest.service.JsonParserService;

@RestController
public class JsonParserController {
	
	private static Logger log = LoggerFactory.getLogger(JsonParserController.class);
	
	@Autowired
	JsonParserService jsonParserService;
	
	@RequestMapping(value = "/parseBook", method = RequestMethod.GET)
	public ResponseEntity<?> parseBook() {

		try {
			BookDTO[] bookDTO = jsonParserService.readTestJsonWithObjectMapper();
			return new ResponseEntity<BookDTO[]>(bookDTO, HttpStatus.OK);
		} catch (RuntimeException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/book/search", method = RequestMethod.GET)
	public ResponseEntity<?> searchClient(@RequestParam("title") String title) {
		try {
			List<BookDTO> list = jsonParserService.searchBook(title);
			return new ResponseEntity<List<BookDTO>>(list, HttpStatus.OK);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return null;
	}

}
