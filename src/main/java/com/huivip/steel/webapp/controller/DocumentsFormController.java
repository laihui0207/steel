package com.huivip.steel.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.huivip.steel.service.DocumentsManager;
import com.huivip.steel.model.Documents;
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
@RequestMapping("/documentsform*")
public class DocumentsFormController extends BaseFormController {
    private DocumentsManager documentsManager = null;

    @Autowired
    public void setDocumentsManager(DocumentsManager documentsManager) {
        this.documentsManager = documentsManager;
    }

    public DocumentsFormController() {
        setCancelView("redirect:documentss");
        setSuccessView("redirect:documentss");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected Documents showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return documentsManager.get(new Long(id));
        }

        return new Documents();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(Documents documents, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(documents, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "documentsform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (documents.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            documentsManager.remove(documents.getId());
            saveMessage(request, getText("documents.deleted", locale));
        } else {
            documentsManager.save(documents);
            String key = (isNew) ? "documents.added" : "documents.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:documentsform?id=" + documents.getId();
            }
        }

        return success;
    }
}
