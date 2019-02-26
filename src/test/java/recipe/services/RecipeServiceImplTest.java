package recipe.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import recipe.commands.RecipeCommand;
import recipe.converters.CategoryCommandToCategory;
import recipe.converters.RecipeCommandToRecipe;
import recipe.converters.RecipeToRecipeCommand;
import recipe.domain.Recipe;
import recipe.repositories.RecipeRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {


    private RecipeService recipeService;

    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private RecipeToRecipeCommand recipeToRecipeCommand;

    @Mock
    private RecipeCommandToRecipe recipeCommandToRecipe;

    @Mock
    private CategoryCommandToCategory categoryCommandToCategory;

    public static final String DESCRIPTION = "Test Changing Description";

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository,recipeToRecipeCommand,recipeCommandToRecipe,categoryCommandToCategory);
    }

    @Test
    public void getRecipes() throws Exception {

        Set<Recipe> recipesData = new HashSet<Recipe>();
        recipesData.add(new Recipe());

        when(recipeRepository.findAll()).thenReturn(recipesData);

        Set<Recipe> recipes = recipeService.getRecipes();
        assertEquals(1,recipes.size());
    }


    @Test
    public void findRecipeById() throws Exception {

        Recipe recipe = new Recipe();
        recipe.setId(1L);

        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        Recipe recipeReturned = recipeService.findRecipeById(1L);

        assertNotNull("Null recipe returned", recipeReturned);

       verify(recipeRepository,times(1)).findById(anyLong());

       verify(recipeRepository,never()).findAll();
    }

}