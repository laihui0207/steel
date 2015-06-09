package com.huivip.steel.webapp.controller;

import com.huivip.steel.service.PostTypeManager;
import com.huivip.steel.model.PostType;

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

public class PostTypeControllerTest extends BaseControllerTestCase {
    @Autowired
    private PostTypeManager postTypeManager;
    @Autowired
    private PostTypeController controller;

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
        mockMvc.perform(get("/postTypes"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("postTypeList"))
            .andExpect(view().name("postTypes"));
    }

    @Test
    public void testSearch() throws Exception {
        // regenerate indexes
        postTypeManager.reindex();

        Map<String,Object> model = mockMvc.perform((get("/postTypes")).param("q", "*"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("postTypeList"))
            .andReturn()
            .getModelAndView()
            .getModel();

        List results = (List) model.get("postTypeList");
        assertNotNull(results);
        assertEquals(3, results.size());
    }
}
