package com.chathu.doc.service;

import com.chathu.doc.model.Document;
import org.dom4j.DocumentException;

import java.util.List;

public interface DocService {
    Document save(Document document) throws DocumentException;
    List<Document> fetch();
    Document fetch(Integer id);
}
