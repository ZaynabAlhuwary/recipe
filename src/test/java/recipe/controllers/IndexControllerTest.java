package recipe.controllers;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import recipe.commands.RecipeCommand;
import recipe.services.RecipeService;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class IndexControllerTest {

    @Mock
    private RecipeService recipeService;

    private IndexController indexController;

    @Mock
    Model model;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        indexController = new IndexController(recipeService);
        mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
    }

   @Test
   public void testMVCBehavior()throws Exception{

        //MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));

   }

    @Test
    public void getRecipes() throws Exception {
        String viewValue = "index";
        assertEquals(viewValue,indexController.getRecipes(model));
        verify(recipeService,times(1)).getRecipes();
        verify(model,times(1)).addAttribute(eq("recipes"),anySet());
    }

   /* @Test
    public void getNewRecipeForm() throws Exception {

        mockMvc.perform(get("/recipe/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipeform"));
                //.andExpect(model().attributeExists("recipe"));
    }
*/
}