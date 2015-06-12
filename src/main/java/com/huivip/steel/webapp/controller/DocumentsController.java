package com.huivip.steel.webapp.controller;

import com.huivip.steel.dao.SearchException;
import com.huivip.steel.service.DocumentsManager;
import com.huivip.steel.model.Documents;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/documentss*")
public class DocumentsController {
    private DocumentsManager documentsManager;

    @Autowired
    public void setDocumentsManager(DocumentsManager documentsManager) {
        this.documentsManager = documentsManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(documentsManager.search(query, Documents.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(documentsManager.getAll());
        }
        return model;
    }
}
