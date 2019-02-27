package recipe.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import recipe.commands.RecipeCommand;
import recipe.converters.CategoryToCategoryCommand;
import recipe.converters.IngredientToIngredientCommand;
import recipe.domain.Recipe;
import recipe.services.CategoryService;
import recipe.services.IngredientService;
import recipe.services.RecipeService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class RecipeControllerTest {
    @Mock
    RecipeService recipeService;
    IngredientService ingredientService;
    CategoryService categoryService;
    CategoryToCategoryCommand catToCatCommand;
    IngredientToIngredientCommand ingredToIngredCommand;


    RecipeController controller;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        controller = new RecipeController(recipeService,ingredientService,categoryService, catToCatCommand, ingredToIngredCommand);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

    }

    @Test
    public void showById() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        when(recipeService.findRecipeById(anyLong())).thenReturn(recipe);

        mockMvc.perform(get("/recipe/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/show"))
                .andExpect(model().attributeExists("recipe"));
    }

    @Test
    public void saveOrUpdate() throws Exception {
        RecipeCommand command = new RecipeCommand();
        command.setId(2L);

        when(recipeService.saveRecipeCommand(any())).thenReturn(command);

        mockMvc.perform(post("/recipe")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)

        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/2/show"));
    }

    @Test
    public void deleteById() throws Exception {
        mockMvc.perform(get("/recipe/1/delete"))
                .andExpect(status().is3xxRedirection()) // test HTML Unit
                .andExpect(view().name("redirect:/"));

        verify(recipeService, times(1)).deleteById(anyLong());
    }
}