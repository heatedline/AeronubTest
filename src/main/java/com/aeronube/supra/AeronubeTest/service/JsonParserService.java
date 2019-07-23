package com.aeronube.supra.AeronubeTest.service;

import java.io.IOException;
import java.util.List;

import com.aeronube.supra.AeronubeTest.dto.BookDTO;

public interface JsonParserService {
	
	public BookDTO[] readTestJsonWithObjectMapper() throws IOException;

	public List<BookDTO> searchBook(String title);

}
