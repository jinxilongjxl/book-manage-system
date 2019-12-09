package com.ibm.ssnb.dao;

import com.ibm.ssnb.entity.Book;
import com.ibm.ssnb.entity.BookType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BookTypeDao extends JpaRepository<BookType, Integer>, JpaSpecificationExecutor<BookType> {
}
