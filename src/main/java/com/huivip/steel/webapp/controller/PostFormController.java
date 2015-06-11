package com.huivip.steel.webapp.controller;

import com.huivip.steel.model.Post;
import com.huivip.steel.model.PostType;
import com.huivip.steel.model.User;
import com.huivip.steel.model.UserGroup;
import com.huivip.steel.service.PostManager;
import com.huivip.steel.service.PostTypeManager;
import com.huivip.steel.service.UserGroupManager;
import com.huivip.steel.service.UserManager;
import com.huivip.steel.util.SteelConfig;
import com.huivip.steel.util.Thumbnail;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyEditorSupport;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/postform*")
public class PostFormController extends BaseFormController {
    private PostManager postManager = null;
    @Autowired
    private PostTypeManager postTypeManager;
    @Autowired
    private UserManager userManager;
    @Autowired
    private UserGroupManager userGroupManager;

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

       /* if (validator != null) { // validator is null during testing
            validator.validate(post, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "postform";
            }
        }*/

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (post.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            postManager.remove(post.getId());
            saveMessage(request, getText("post.deleted", locale));
        } else {
            if(!post.isIfAccessAllReply()) {
                String[] replayUsers = request.getParameterValues("replyUsers");
                String[] replyGroups = request.getParameterValues("replyGroups");
                if(null!=replayUsers){
                    post.getReplyUsers().clear();
                    for(String userID:replayUsers){
                        post.getReplyUsers().add(userManager.get(Long.parseLong(userID)));
                    }
                }
                if(null!=replyGroups){
                    post.getReplyGroups().clear();
                    for(String groupId:replyGroups){
                        post.getReplyGroups().add(userGroupManager.get(Long.parseLong(groupId)));
                    }
                }
            }

            final User cleanUser = getUserManager().getUserByUsername(
                    request.getRemoteUser());
            post.setCreater(cleanUser);
            handleThumbnail(post);
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
    @ModelAttribute("userList")
    public List<User> userList(){
        return userManager.getAll();
    }
    @ModelAttribute("userGroupList")
    public List<UserGroup> userGroupList(){
        return userGroupManager.getAll();
    }
    private void handleThumbnail(Post post) {
        String imgRegex = "<img.*?(?: |\\t|\\r|\\n)?src=['\"]?(.+?)['\"]?(?:(?: |\\t|\\r|\\n)+.*?)?>";
        Pattern r = Pattern.compile(imgRegex);
        Matcher m = r.matcher(post.getContent());
        String attacheDir = SteelConfig.getConfigureAttachDir();
        if (null == attacheDir || attacheDir.length() == 0) {
            attacheDir = getServletContext().getRealPath("/");
        }
        if (!attacheDir.endsWith("/")) {
            attacheDir += "/";
        }
        while (m.find()) {
            String imgUrl = m.group(1);
            if (imgUrl.indexOf("attached") < 0) {
                continue;
            }
            String fileUrl = attacheDir + imgUrl;
            //to do  check if need create thumbnail
            Thumbnail.thumbnail_create(fileUrl.substring(0, fileUrl.lastIndexOf("/") + 1),
                    fileUrl.substring(fileUrl.lastIndexOf("/") + 1));
            String thumbnailURL = imgUrl.substring(0, imgUrl.lastIndexOf("/") + 1) + imgUrl.substring(imgUrl.lastIndexOf("/") + 1,
                    imgUrl.lastIndexOf(".")) + "_smaller" + imgUrl.substring(imgUrl.lastIndexOf("."));
            post.setThumbnailUrl(thumbnailURL);
            break;
        }
    }

    @InitBinder
    public void binder(WebDataBinder binder) {
        /*binder.registerCustomEditor(Timestamp.class,
                new PropertyEditorSupport() {
                    public void setAsText(String value) {
                        try {
                            Date parsedDate = new SimpleDateFormat("dd/MM/yyyy").parse(value);
                            setValue(new Timestamp(parsedDate.getTime()));
                        } catch (ParseException e) {
                            setValue(null);
                        }
                    }
                });*/
        binder.registerCustomEditor(UserGroup.class, "id", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                UserGroup type = (UserGroup) userGroupManager.get(Long.parseLong(text));
                setValue(type);
            }
        });
    }
}
