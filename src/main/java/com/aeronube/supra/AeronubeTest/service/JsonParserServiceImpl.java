package com.aeronube.supra.AeronubeTest.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.aeronube.supra.AeronubeTest.dto.BookDTO;
import com.aeronube.supra.AeronubeTest.dto.ReviewDTO;
import com.aeronube.supra.AeronubeTest.model.Book;
import com.aeronube.supra.AeronubeTest.model.Review;
import com.aeronube.supra.AeronubeTest.repository.BookRepository;
import com.aeronube.supra.AeronubeTest.repository.ReviewRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service("JsonParserService")
@Transactional
public class JsonParserServiceImpl implements JsonParserService {
	
	private static Logger log = LoggerFactory.getLogger(JsonParserServiceImpl.class);
	
	@Autowired
	private ResourceLoader resourceLoader;
	@Autowired
	Mapper mapper;
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private ReviewRepository reviewRepository;

	@Override
	public BookDTO[] readTestJsonWithObjectMapper() throws IOException {
		// TODO Auto-generated method stub
        ObjectMapper objectMapper = new ObjectMapper();

        BookDTO[] bookDTOArr = objectMapper.readValue(new File(resourceLoader.getResource("classpath:test.json").getURI().getPath()), BookDTO[].class);

        log.info(bookDTOArr.toString());
        
        
        for(BookDTO bookDTO : bookDTOArr) {
        	Book book = mapper.map(bookDTO, Book.class);
        	bookRepository.save(book);
        	for(ReviewDTO reviewDTO : bookDTO.getReviews()) {
        		//Book book2 = bookRepository.findOne(id)
            	Review review = mapper.map(reviewDTO, Review.class);
            	review.setBook(book);
        		//System.out.println(reviewDTO.toString());
        		reviewRepository.save(review);
        	}
        }
        
        return bookDTOArr;
	}

	@Override
	public List<BookDTO> searchBook(String title) {
		// TODO Auto-generated method stub
		List<BookDTO> bookDTOList = new ArrayList<BookDTO>(); 
		List<Book> searchedBookList = bookRepository.searchBookByTitle("%" + title + "%");
		if(searchedBookList != null && searchedBookList.size() > 0) {
			for(Book book : searchedBookList) {
				BookDTO bookDTO = mapper.map(book, BookDTO.class);
				bookDTOList.add(bookDTO);
			}
		} else {
			bookDTOList = new ArrayList<BookDTO>(); 
		}
		return bookDTOList;
	}

	
}
