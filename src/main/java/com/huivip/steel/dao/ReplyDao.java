package com.huivip.steel.dao;

import com.huivip.steel.model.Reply;

import javax.ws.rs.PathParam;
import java.util.List;

/**
 * An interface that provides a data management interface to the Reply table.
 */
public interface ReplyDao extends GenericDao<Reply, Long> {

    List<Reply> repliesOfPost(@PathParam("postID")String postID);

}