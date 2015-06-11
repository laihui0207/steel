package com.huivip.steel.webapp.controller;

import com.huivip.steel.model.Post;
import com.huivip.steel.model.Reply;
import com.huivip.steel.model.User;
import com.huivip.steel.service.PostManager;
import com.huivip.steel.service.ReplyManager;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Controller
@RequestMapping("/replyform")
public class ReplyFormController extends BaseFormController {
    private ReplyManager replyManager = null;
    @Autowired
    private PostManager postManager;

    @Autowired
    public void setReplyManager(ReplyManager replyManager) {
        this.replyManager = replyManager;
    }

    public ReplyFormController() {
        setCancelView("redirect:replies");
        setSuccessView("redirect:replies");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected Reply showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return replyManager.get(new Long(id));
        }
        Reply reply=new Reply();
        return reply;
    }


    @RequestMapping(method = RequestMethod.GET,value = "{postID}")
    public ModelAndView showForm(@PathVariable("postID")String postID){
        ModelAndView model=new ModelAndView("replyform");
        Reply reply=new Reply();
        reply.setPost(postManager.get(Long.parseLong(postID)));
        model.addObject("reply",reply);
        return  model;
    }
    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(Reply reply, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(reply, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "replyform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (reply.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            replyManager.remove(reply.getId());
            saveMessage(request, getText("reply.deleted", locale));
        } else {
            final User cleanUser = getUserManager().getUserByUsername(
                    request.getRemoteUser());
            reply.setReplier(cleanUser);
            replyManager.save(reply);
            Post post=postManager.get(reply.getPost().getId());
            post.setLastReplyTime(post.getCreateTime());
            post.setLastReplyUser(cleanUser);
            postManager.save(post);
            String key = (isNew) ? "reply.added" : "reply.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:replyform?id=" + reply.getId();
            }
        }

        return success;
    }
}
