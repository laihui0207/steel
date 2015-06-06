package com.huivip.steel.webapp.controller;

import com.huivip.steel.dao.SearchException;
import com.huivip.steel.service.ReplyManager;
import com.huivip.steel.model.Reply;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/replies*")
public class ReplyController {
    private ReplyManager replyManager;

    @Autowired
    public void setReplyManager(ReplyManager replyManager) {
        this.replyManager = replyManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(replyManager.search(query, Reply.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(replyManager.getAll());
        }
        return model;
    }
}
