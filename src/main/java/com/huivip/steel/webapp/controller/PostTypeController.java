package com.huivip.steel.webapp.controller;

import com.huivip.steel.dao.SearchException;
import com.huivip.steel.service.PostTypeManager;
import com.huivip.steel.model.PostType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/postTypes*")
public class PostTypeController {
    private PostTypeManager postTypeManager;

    @Autowired
    public void setPostTypeManager(PostTypeManager postTypeManager) {
        this.postTypeManager = postTypeManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(postTypeManager.search(query, PostType.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(postTypeManager.getAll());
        }
        return model;
    }
}
