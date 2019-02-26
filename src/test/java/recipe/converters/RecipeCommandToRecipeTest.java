/*
 *  Created By  Zaynab Osama ,  On 2/24/19 4:39 AM
 *
 */

package recipe.converters;

import org.junit.Before;
import org.junit.Test;
import recipe.commands.CategoryCommand;
import recipe.commands.IngredientCommand;
import recipe.commands.NoteCommand;
import recipe.commands.RecipeCommand;
import recipe.domain.Difficulty;
import recipe.domain.Recipe;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class RecipeCommandToRecipeTest {

    public static final Long RECIPE_ID = 1L;
    public static final Integer COOK_TIME = Integer.valueOf("5");
    public static final Integer PREP_TIME = Integer.valueOf("7");
    public static final String DESCRIPTION = "My Recipe";
    public static final String DIRECTIONS = "Directions";
    public static final Difficulty DIFFICULTY = Difficulty.EASY;
    public static final Integer SERVINGS = Integer.valueOf("3");
    public static final String SOURCE = "Source";
    public static final String URL = "localhost://8989/test";
    public static final Long CAT_ID_1 = 1L;
    public static final Long CAT_ID2 = 2L;
    public static final Long INGRED_ID_1 = 3L;
    public static final Long INGRED_ID_2 = 4L;
    public static final Long NOTES_ID = 9L;

    RecipeCommandToRecipe converter;


    @Before
    public void setUp() throws Exception {
        converter = new RecipeCommandToRecipe(new CategoryCommandToCategory(),
                new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()),
                new NoteCommandToNote());
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new RecipeCommand()));
    }

    @Test
    public void convert() throws Exception {
        //given
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(RECIPE_ID);
        recipeCommand.setCookTime(COOK_TIME);
        recipeCommand.setPrepTime(PREP_TIME);
        recipeCommand.setDescription(DESCRIPTION);
        recipeCommand.setDifficulty(DIFFICULTY);
        recipeCommand.setDirections(DIRECTIONS);
       /* recipeCommand.setServings(SERVINGS);
        recipeCommand.setSource(SOURCE);*/
        recipeCommand.setUrl(URL);

        NoteCommand notes = new NoteCommand();
        notes.setId(NOTES_ID);

        recipeCommand.setNote(notes);

        CategoryCommand category = new CategoryCommand();
        category.setId(3L);
        category.setDescription("test");

        CategoryCommand category2 = new CategoryCommand();
        category.setId(CAT_ID2);

        /*Set<CategoryCommand> recipeCommandCat = new HashSet<>();
        recipeCommandCat.add(category);
        recipeCommandCat.add(category2);
*/

        recipeCommand.setCategoryCommand(category);
        IngredientCommand ingredient = new IngredientCommand();
        ingredient.setId(INGRED_ID_1);

        recipeCommand.getIngredients().add(ingredient);

        //when
        Recipe recipe  = converter.convert(recipeCommand);

        assertNotNull(recipe);
        assertEquals(RECIPE_ID, recipe.getId());
        assertEquals(COOK_TIME, recipe.getCookTime());
        assertEquals(PREP_TIME, recipe.getPrepTime());
        assertEquals(DESCRIPTION, recipe.getDescription());
        assertEquals(DIFFICULTY, recipe.getDifficulty());
        assertEquals(DIRECTIONS, recipe.getDirections());
       /* assertEquals(SERVINGS, recipe.getServings());
        assertEquals(SOURCE, recipe.getSource());*/
        assertEquals(URL, recipe.getUrl());
        assertEquals(NOTES_ID, recipe.getNote().getId());
        assertEquals(1, recipe.getIngredients().size());
    }
}