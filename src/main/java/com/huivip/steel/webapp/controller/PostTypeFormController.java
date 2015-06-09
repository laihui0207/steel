package com.huivip.steel.webapp.controller;

import com.huivip.steel.model.PostType;
import com.huivip.steel.model.User;
import com.huivip.steel.service.PostTypeManager;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Controller
@RequestMapping("/postTypeform*")
public class PostTypeFormController extends BaseFormController {
    private PostTypeManager postTypeManager = null;

    @Autowired
    public void setPostTypeManager(PostTypeManager postTypeManager) {
        this.postTypeManager = postTypeManager;
    }

    public PostTypeFormController() {
        setCancelView("redirect:postTypes");
        setSuccessView("redirect:postTypes");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected PostType showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return postTypeManager.get(new Long(id));
        }

        return new PostType();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(PostType postType, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(postType, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "postTypeform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (postType.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            postTypeManager.remove(postType.getId());
            saveMessage(request, getText("postType.deleted", locale));
        } else {
            final User cleanUser = getUserManager().getUserByUsername(
                    request.getRemoteUser());
            postType.setCreater(cleanUser);
            postType.setUpdater(cleanUser);
            postTypeManager.save(postType);
            String key = (isNew) ? "postType.added" : "postType.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:postTypeform?id=" + postType.getId();
            }
        }

        return success;
    }
}
