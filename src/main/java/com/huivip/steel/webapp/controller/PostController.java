package com.huivip.steel.webapp.controller;

import com.huivip.steel.dao.SearchException;
import com.huivip.steel.model.Post;
import com.huivip.steel.service.PostManager;
import com.huivip.steel.service.ReplyManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/posts*")
public class PostController {
    private PostManager postManager;
    @Autowired
    private ReplyManager replyManager;

    @Autowired
    public void setPostManager(PostManager postManager) {
        this.postManager = postManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(postManager.search(query, Post.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(postManager.getAll());
        }
        return model;
    }
    @RequestMapping(method = RequestMethod.GET,value = "{postID}/replies")
    public ModelAndView repliesOfPost(@PathVariable("postID")String postID){
        ModelAndView view=new ModelAndView("replies");
        view.addObject("replyList",replyManager.repliesOfPost(postID));
        view.addObject("post",postManager.get(Long.parseLong(postID)));
        return view;
    }

}
