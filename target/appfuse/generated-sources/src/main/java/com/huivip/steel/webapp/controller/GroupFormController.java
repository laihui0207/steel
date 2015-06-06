package com.huivip.steel.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.huivip.steel.service.GroupManager;
import com.huivip.steel.model.Group;
import com.huivip.steel.webapp.controller.BaseFormController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Controller
@RequestMapping("/groupform*")
public class GroupFormController extends BaseFormController {
    private GroupManager groupManager = null;

    @Autowired
    public void setGroupManager(GroupManager groupManager) {
        this.groupManager = groupManager;
    }

    public GroupFormController() {
        setCancelView("redirect:groups");
        setSuccessView("redirect:groups");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected Group showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return groupManager.get(new Long(id));
        }

        return new Group();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(Group group, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(group, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "groupform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (group.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            groupManager.remove(group.getId());
            saveMessage(request, getText("group.deleted", locale));
        } else {
            groupManager.save(group);
            String key = (isNew) ? "group.added" : "group.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:groupform?id=" + group.getId();
            }
        }

        return success;
    }
}
