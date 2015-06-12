package com.huivip.steel.webapp.controller;

import com.huivip.steel.service.DocumentsManager;
import com.huivip.steel.model.Documents;

import com.huivip.steel.webapp.controller.BaseControllerTestCase;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class DocumentsControllerTest extends BaseControllerTestCase {
    @Autowired
    private DocumentsManager documentsManager;
    @Autowired
    private DocumentsController controller;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/pages/");
        viewResolver.setSuffix(".jsp");

        mockMvc = MockMvcBuilders.standaloneSetup(controller).setViewResolvers(viewResolver).build();
    }

    @Test
    public void testHandleRequest() throws Exception {
        mockMvc.perform(get("/documentss"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("documentsList"))
            .andExpect(view().name("documentss"));
    }

    @Test
    public void testSearch() throws Exception {
        // regenerate indexes
        documentsManager.reindex();

        Map<String,Object> model = mockMvc.perform((get("/documentss")).param("q", "*"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("documentsList"))
            .andReturn()
            .getModelAndView()
            .getModel();

        List results = (List) model.get("documentsList");
        assertNotNull(results);
        assertEquals(3, results.size());
    }
}
