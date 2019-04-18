package com.chathu.doc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Section{

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    private Integer id;
    private String name;


    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Page page;

    @OneToMany(mappedBy = "section",cascade = CascadeType.ALL)
    List<Paragraph> paragraph;

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

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public List<Paragraph> getParagraph() {
        return paragraph;
    }

    public void setParagraph(List<Paragraph> paragraph) {
        this.paragraph = paragraph;
    }
}
