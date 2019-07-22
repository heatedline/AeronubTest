package com.aeronube.supra.AeronubeTest.service;

import java.io.File;
import java.io.IOException;

import javax.transaction.Transactional;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.aeronube.supra.AeronubeTest.dto.BookDTO;
import com.aeronube.supra.AeronubeTest.model.Book;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service("JsonParserService")
@Transactional
public class JsonParserServiceImpl implements JsonParserService {
	
	private static Logger log = LoggerFactory.getLogger(JsonParserServiceImpl.class);
	
	@Autowired
	private ResourceLoader resourceLoader;
	@Autowired
	Mapper mapper;

	@Override
	public BookDTO[] readTestJsonWithObjectMapper() throws IOException {
		// TODO Auto-generated method stub
        ObjectMapper objectMapper = new ObjectMapper();

        BookDTO[] bookDTO = objectMapper.readValue(new File(resourceLoader.getResource("classpath:test.json").getURI().getPath()), BookDTO[].class);

        log.info(bookDTO.toString());
        
        return bookDTO;
	}

	
}
