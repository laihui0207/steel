package com.huivip.steel.service;

import com.huivip.steel.model.Post;
import com.huivip.steel.model.Reply;

import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

@WebService
@Path("/posts")
public interface PostManager extends GenericManager<Post, Long> {

    @GET
    List<Post> postList();

    @GET
    @Path("${postID}")
    Post postById(@PathParam("postID") String postID);

    @GET
    @Path("${postID}/replies")
    List<Reply> replyListOfPost(@PathParam("postID") String postID);
}