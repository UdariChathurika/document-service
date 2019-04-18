package com.chathu.doc.repository;

import com.chathu.doc.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocRepository extends JpaRepository<Document, Integer> {

}

