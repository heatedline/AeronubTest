package com.aeronube.supra.AeronubeTest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aeronube.supra.AeronubeTest.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer>{

	@Query("select b from Book b where b.title like :title")
	List<Book> searchBookByTitle(@Param(value = "title") String title);
	
}
