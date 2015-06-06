package com.huivip.steel.service;

import com.huivip.steel.service.GenericManager;
import com.huivip.steel.model.Group;

import java.util.List;
import javax.jws.WebService;

@WebService
public interface GroupManager extends GenericManager<Group, Long> {
    
}