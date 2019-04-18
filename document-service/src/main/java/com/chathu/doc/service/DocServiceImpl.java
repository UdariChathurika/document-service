package com.chathu.doc.service;

import com.chathu.doc.model.*;
import com.chathu.doc.repository.DocRepository;
import com.sun.org.apache.regexp.internal.RE;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocServiceImpl implements DocService{
    @Autowired
    DocRepository docRepository;

    @Override
    public Document save(Document document) throws DocumentException {
        if(fetch(document.getId())!=null) {
            throw new DocumentException("Document id already exist");
        }
                for (Card card : document.getCard()) {
                    card.setDocument(document);
                    for (Page page : card.getPage()) {
                        page.setCard(card);
                        for (Section section : page.getSection()) {
                            section.setPage(page);
                            for (Paragraph paragraph : section.getParagraph()) {
                                paragraph.setSection(section);
                            }
                        }
                    }
                }
            return docRepository.save(document);
    }

    @Override
    public List<Document> fetch() {
        return docRepository.findAll();
    }

    @Override
    public Document fetch(Integer id) {
        Optional<Document> document = docRepository.findById(id);
        if(document.isPresent()) {
            return document.get();
        }else {

        return null;
        }
    }



}
