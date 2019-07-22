package com.aeronube.supra.AeronubeTest.service;

import java.io.IOException;

import com.aeronube.supra.AeronubeTest.dto.BookDTO;

public interface JsonParserService {
	
	public BookDTO[] readTestJsonWithObjectMapper() throws IOException;

}
