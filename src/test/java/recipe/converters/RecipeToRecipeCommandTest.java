package recipe.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import recipe.commands.RecipeCommand;
import recipe.domain.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class RecipeToRecipeCommandTest {

    @Mock
    CategoryToCategoryCommand categoryConveter;
    @Mock
    IngredientToIngredientCommand ingredientConverter;
    @Mock
    NotesToNoteCommand notesConverter;

    RecipeToRecipeCommand recipeToRecipeCommand;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeToRecipeCommand = new RecipeToRecipeCommand(categoryConveter,ingredientConverter,notesConverter);

    }


    @Test
    public void convert() {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        recipe.setDescription("description");
        recipe.setCookTime(2);
        recipe.setCategory(new Category());
        recipe.addIngredient(new Ingredient());
        recipe.setDifficulty(Difficulty.EASY);
        recipe.setNote(new Note());

        // when
        RecipeCommand recipeCommand = recipeToRecipeCommand.convert(recipe);
        assertNotNull(recipeCommand);
        assertTrue(recipeCommand instanceof RecipeCommand);


    }
}