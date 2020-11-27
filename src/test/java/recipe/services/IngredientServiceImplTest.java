package recipe.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import recipe.commands.IngredientCommand;
import recipe.converters.IngredientCommandToIngredient;
import recipe.converters.IngredientToIngredientCommand;
import recipe.domain.Ingredient;
import recipe.domain.Recipe;
import recipe.repositories.IngredientRepository;
import recipe.repositories.RecipeRepository;
import recipe.repositories.UnitOfMeasureRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class IngredientServiceImplTest {

    @Mock
    IngredientRepository ingredientRepository;
    @Mock
    RecipeRepository recipeRepository;
    @Mock
    IngredientToIngredientCommand ingredientToIngredientCommand;
    @Mock
    IngredientCommandToIngredient ingredientCommandToIngredient;
    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    IngredientServiceImpl ingredientService;

    @BeforeEach
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        ingredientService = new IngredientServiceImpl(ingredientRepository,recipeRepository,ingredientToIngredientCommand,ingredientCommandToIngredient,unitOfMeasureRepository);
    }

    @Test
    public void getIngredientById() {

        //setup
        Ingredient ingredient = new Ingredient();

        //given
        when(ingredientRepository.findById(anyLong())).thenReturn(Optional.of(ingredient));

        Optional<Recipe> optionalRecipe = recipeRepository.findById(anyLong());
        assertNotNull(optionalRecipe);
        verify(recipeRepository,times(1)).findById(anyLong());
    }

    @Disabled
    @Test//(expected = NullPointerException.class)
    public void findByRecipeIdAndIngredientId() {

        //given
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(1L);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(1L);

        Ingredient ingredient3 = new Ingredient();
        ingredient3.setId(3L);

       /* recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);*/
        recipe.addIngredient(ingredient3);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        //then
        IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndIngredientId(1L, 3L);

        //when
        assertEquals(Long.valueOf(3L), ingredientCommand.getId());
        assertEquals(Long.valueOf(1L), ingredientCommand.getRecipeId());
        verify(recipeRepository, times(1)).findById(anyLong());
    }

    @Test
    public void deleteById() {
        // setup fake recipe with fake deleted obj -> ingredient
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Ingredient ingredient = new Ingredient();
        ingredient.setId(2L);
        recipe.addIngredient(ingredient);

        Optional<Recipe> optionalRecipe  = Optional.of(recipe);

        //given
        when(recipeRepository.findById(anyLong())).thenReturn(optionalRecipe);

        //when
        ingredientService.deleteById(1L,2L);

        //then make verification

        verify(recipeRepository,times(1)).findById(anyLong());
        verify(recipeRepository,times(1)).save(any(Recipe.class));

    }
}