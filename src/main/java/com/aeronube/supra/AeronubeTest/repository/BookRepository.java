package com.aeronube.supra.AeronubeTest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aeronube.supra.AeronubeTest.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer>{

}
