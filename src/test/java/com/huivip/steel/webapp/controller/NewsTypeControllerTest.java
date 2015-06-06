package com.huivip.steel.webapp.controller;

import com.huivip.steel.service.NewsTypeManager;
import com.huivip.steel.model.NewsType;

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

public class NewsTypeControllerTest extends BaseControllerTestCase {
    @Autowired
    private NewsTypeManager newsTypeManager;
    @Autowired
    private NewsTypeController controller;

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
        mockMvc.perform(get("/newsTypes"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("newsTypeList"))
            .andExpect(view().name("newsTypes"));
    }

    @Test
    public void testSearch() throws Exception {
        // regenerate indexes
        newsTypeManager.reindex();

        Map<String,Object> model = mockMvc.perform((get("/newsTypes")).param("q", "*"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("newsTypeList"))
            .andReturn()
            .getModelAndView()
            .getModel();

        List results = (List) model.get("newsTypeList");
        assertNotNull(results);
        assertEquals(3, results.size());
    }
}
