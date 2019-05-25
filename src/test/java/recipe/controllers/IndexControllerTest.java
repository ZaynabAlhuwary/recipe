package recipe.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import recipe.domain.Recipe;
import recipe.services.RecipeService;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class IndexControllerTest {
    @Mock
    RecipeService recipeService;

    @Mock
    Model model;

    IndexController indexController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        indexController = new IndexController(recipeService);
    }

    @Test
    public void getRecipes() throws Exception {
        //setup
        mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
        //1-test get request
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));

        // 2-test returned set of recipes
        Set<Recipe> recipes =new HashSet<Recipe>();
        recipes.add(new Recipe());

        //given
        when(recipeService.getRecipes()).thenReturn(recipes);

        //verify
        verify(recipeService,times(1)).getRecipes();
        assertEquals(1,recipeService.getRecipes().size());
        assertEquals("index",indexController.getRecipes(model));
    }
}