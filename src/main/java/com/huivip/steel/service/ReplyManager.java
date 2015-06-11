package com.huivip.steel.service;

import com.huivip.steel.model.Reply;

import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

@WebService
public interface ReplyManager extends GenericManager<Reply, Long> {
    @GET
    @Path("/posts/{postID}/replies")
    List<Reply> repliesOfPost(@PathParam("postID")String postID);
}