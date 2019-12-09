package com.ibm.ssnb.dao;

import com.ibm.ssnb.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BookDao extends JpaRepository<Book, Integer>, JpaSpecificationExecutor<Book> {
}
