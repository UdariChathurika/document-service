package com.chathu.doc.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Document {

    @Id
    private Integer id;
    private String name;

    @OneToMany(mappedBy = "document",cascade = CascadeType.ALL)
    List<Card> card;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="document_publisher",
            joinColumns = @JoinColumn(name="did",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="pid",referencedColumnName = "id")
    )
    List<Publisher> publisher;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<Card> getCard() {
        return card;
    }

    public void setCard(List<Card> card) {
        this.card = card;
    }

    public List<Publisher> getPublisher() {
        return publisher;
    }

    public void setPublisher(List<Publisher> publisher) {
        this.publisher = publisher;
    }
}
