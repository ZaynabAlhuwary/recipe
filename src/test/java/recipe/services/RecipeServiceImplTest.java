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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {

    @Mock
    RecipeRepository recipeRepository;
    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;
    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;
    @Mock
    CategoryCommandToCategory categoryCommandToCategory;

    RecipeServiceImpl recipeService;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository,recipeToRecipeCommand,recipeCommandToRecipe,categoryCommandToCategory);
    }


    @Test
    public void findRecipeById() {
        //setup
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        //given
        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        assertNotNull(recipeRepository.findById(anyLong()));
        verify(recipeRepository,times(1)).findById(anyLong());
        verify(recipeRepository,never()).findAll();

    }

    @Test
    public void findByDesc() {
        //setup
        Recipe recipe = new Recipe();
        recipe.setDescription("description");


        //given
        when(recipeRepository.findBydescription(anyString())).thenReturn(Optional.of(recipe));

        Recipe returnedRecipe = recipeRepository.findBydescription(anyString()).get();

        assertEquals("description",returnedRecipe.getDescription());
        verify(recipeRepository,times(1)).findBydescription(anyString());
    }

    @Test
    public void findCommandById() {
        //setup
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);

        Recipe recipe = new Recipe();
        recipe.setId(1L);

        //given
        when(recipeToRecipeCommand.convert(any())).thenReturn(recipeCommand);
        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));

        RecipeCommand returnedRecipeCommand = recipeService.findCommandById(anyLong());

        assertNotNull(returnedRecipeCommand);
       verify(recipeRepository,times(1)).findById(anyLong());
    }

    @Test
    public void deleteById() {

        //setup
        Recipe recipe = new Recipe();
         recipe.setId(1L);

         //when
        recipeRepository.deleteById(anyLong());
        verify(recipeRepository,times(1)).deleteById(anyLong());
    }
}