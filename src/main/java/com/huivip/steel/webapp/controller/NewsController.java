package com.huivip.steel.webapp.controller;

import com.huivip.steel.dao.SearchException;
import com.huivip.steel.model.News;
import com.huivip.steel.service.NewsManager;
import com.huivip.steel.service.NewsTypeManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/newss*")
public class NewsController {
    private NewsManager newsManager;
    @Autowired
    private NewsTypeManager newsTypeManager;

    @Autowired
    public void setNewsManager(NewsManager newsManager) {
        this.newsManager = newsManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
            throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(newsManager.search(query, News.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(newsManager.getAll());
        }
        return model;
    }
    @RequestMapping(method = RequestMethod.GET,value = "/newstype")
    public ModelAndView listNewsByNewsType(HttpServletRequest request){
        String id=request.getParameter("id");
        if(null==id || id.length()==0){
            return null;
        }
        ModelAndView view=new ModelAndView("/newss");
        view.addObject("newsList",newsManager.newsListOfNewsType(id));
        return view;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/view/{id}")
    @ResponseBody
    public String viewNews(@PathVariable("id")String id,ModelMap model) {
        News news = newsManager.get(Long.parseLong(id));
        return news.getContent();
    }
}
