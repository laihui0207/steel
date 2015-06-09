package com.huivip.steel.webapp.controller;

import com.huivip.steel.model.Post;
import com.huivip.steel.model.PostType;
import com.huivip.steel.model.User;
import com.huivip.steel.service.PostManager;
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
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/postform*")
public class PostFormController extends BaseFormController {
    private PostManager postManager = null;
    @Autowired
    private PostTypeManager postTypeManager;

    @Autowired
    public void setPostManager(PostManager postManager) {
        this.postManager = postManager;
    }

    public PostFormController() {
        setCancelView("redirect:posts");
        setSuccessView("redirect:posts");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected Post showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return postManager.get(new Long(id));
        }

        return new Post();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(Post post, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(post, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "postform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (post.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            postManager.remove(post.getId());
            saveMessage(request, getText("post.deleted", locale));
        } else {
            final User cleanUser = getUserManager().getUserByUsername(
                    request.getRemoteUser());
            post.setCreater(cleanUser);

            postManager.save(post);
            String key = (isNew) ? "post.added" : "post.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:postform?id=" + post.getId();
            }
        }

        return success;
    }
    @ModelAttribute("postTypeList")
    public List<PostType> getPostTypeList(){

        return postTypeManager.getAll();
    }
}
