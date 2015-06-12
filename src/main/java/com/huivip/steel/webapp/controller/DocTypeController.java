package com.huivip.steel.webapp.controller;

import com.huivip.steel.dao.SearchException;
import com.huivip.steel.service.DocTypeManager;
import com.huivip.steel.model.DocType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/docTypes*")
public class DocTypeController {
    private DocTypeManager docTypeManager;

    @Autowired
    public void setDocTypeManager(DocTypeManager docTypeManager) {
        this.docTypeManager = docTypeManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(docTypeManager.search(query, DocType.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(docTypeManager.getAll());
        }
        return model;
    }
}
