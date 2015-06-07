package com.huivip.steel.service;

import com.huivip.steel.model.UserGroup;

import javax.jws.WebService;
import javax.ws.rs.*;
import java.util.List;

@WebService
@Path("/ugroups")
public interface UserGroupManager extends GenericManager<UserGroup, Long>  {

    @GET
    List<UserGroup> getAllUserGroups();

    @GET
    @Path("{id}")
    UserGroup getUserGroup(@PathParam("id") String id);

    @POST
    UserGroup saveUserGroup(UserGroup userGroup);

    @DELETE
    @Path("{id}")
    String removeUserGroup(@PathParam("id") String id);
}