package com.chathu.doc.controller;

import com.chathu.doc.model.Document;
import com.chathu.doc.model.Tree;
import com.chathu.doc.service.DocService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.dom4j.DocumentException;
import org.json.JSONTokener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping(value ="/api")
public class DocController {

        @Autowired
        DocService docService;

        @RequestMapping(value = "/document", method = RequestMethod.POST)
        public ResponseEntity<Document> save(@RequestBody Document document) throws DocumentException {

            try {
                Document document2 = docService.save(document);
                return ResponseEntity.ok(document2);
            } catch (DocumentException e) {
                e.printStackTrace();
                return ResponseEntity.badRequest().build();
            }
        }

        @RequestMapping(value = "/document", method = RequestMethod.GET)
        public List<Document> fetch(){
            return docService.fetch();
        }

        @RequestMapping(value = "/document/{id}", method = RequestMethod.GET)
        public Document fetch(@PathVariable Integer id){
            if(id<=0) {
                return null;
            }else{
                Document document = docService.fetch(id);
                if(document == null) {
                    return null;
                }
                else {

                    //----------------------------------------------------------------------

                    // Creating Object of ObjectMapper define in Jakson Api
                    ObjectMapper Obj = new ObjectMapper();

                    try {

                        // get document object as a json string
                        String jsonStr = Obj.writeValueAsString(document);

                        System.out.println(jsonStr);

                        JSONObject obj= new JSONObject(jsonStr);
                        JSONArray jsonArray = new JSONArray();
                        //simply put obj into jsonArray
                        jsonArray.put(obj);

                        for (int it = 0; it < jsonArray.length(); it++) {
                            JSONObject contactItem = jsonArray.getJSONObject(it);

                            JSONArray item = contactItem.getJSONArray("card");

                            for (int i = 0; i < item.length(); i++) {

                                JSONObject contactItem2 = item.getJSONObject(i);

                                JSONArray item2 = contactItem2.getJSONArray("page");

                                for (int j = 0; j < item2.length(); j++) {

                                    JSONObject contactItem3 = item2.getJSONObject(j);

                                    JSONArray item3 = contactItem3.getJSONArray("section");

                                    for (int k = 0; k < item3.length(); k++) {

                                        JSONObject contactItem4 = item3.getJSONObject(k);

                                        JSONArray item4 = contactItem4.getJSONArray("paragraph");

                                        for (int l = 0; l < item4.length(); l++) {
                                            String name = item4.getJSONObject(l).getString("name");
                                            System.out.println(name);
                                        }

                                    }

                                }

                            }

                        }
                    }

                    catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }

                    //----------------------------------------------------------------------
                        return document;
                    }
                }
        }
}
