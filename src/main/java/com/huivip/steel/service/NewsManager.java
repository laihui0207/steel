package com.huivip.steel.service;

import com.huivip.steel.model.News;
import org.springframework.web.bind.annotation.RequestParam;

import javax.jws.WebService;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

@WebService
@Path("/newsList")
public interface NewsManager extends GenericManager<News, Long> {

    @GET
    List<News> newsList();

    @GET
    @Path("/query")
    List<News> query(@RequestParam("q") String q);

    @GET
    @Path("/newstype/{id}")
    List<News> newsListOfNewsType(@PathParam("id") String newsTypeId);

    @DELETE
    @Path("{id}")
    void deleteNews(@PathParam("id") String id);


}