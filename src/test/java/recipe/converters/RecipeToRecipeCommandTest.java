/*
 *  Created By  Zaynab Osama ,  On 2/24/19 2:53 AM
 *
 */

package recipe.converters;

import org.junit.Before;
import org.junit.Test;
import recipe.commands.RecipeCommand;
import recipe.domain.*;

import static org.junit.Assert.*;

public class RecipeToRecipeCommandTest {

    public static final Long RECIPEID = 1L;
    public static final Integer COOKTIME = Integer.valueOf("5");
    public static final Integer PREPTIME = Integer.valueOf("7");
    public static final String DESCRIPTION = "My Recipe";
    public static final String DIRECTIONS = "Directions";
    public static final Difficulty DIFFICULTY = Difficulty.EASY;
    public static final Integer SERVINGS = Integer.valueOf("3");
    public static final String SOURCE = "Source";
    public static final String URL = "Some URL";
    public static final Long CAT_ID1 = 1L;
    public static final Long CAT_ID2 = 2L;
    public static final Long INGRED1_ID = 3L;
    public static final Long INGRED2_ID = 4L;
    public static final Long NOTES_ID = 9L;
    RecipeToRecipeCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new RecipeToRecipeCommand(
                new CategoryToCategoryCommand(),
                new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()),
                new NotesToNoteCommand());
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new Recipe()));
    }

    @Test
    public void convert() throws Exception {
        //given
        Recipe recipe = new Recipe();
        recipe.setId(RECIPEID);
        recipe.setCookTime(COOKTIME);
        recipe.setPrepTime(PREPTIME);
        recipe.setDescription(DESCRIPTION);
        recipe.setDifficulty(DIFFICULTY);
        recipe.setDirections(DIRECTIONS);
       /* recipe.setServings(SERVINGS);
        recipe.setSource(SOURCE);*/
        recipe.setUrl(URL);

        Note note = new Note();
        note.setId(NOTES_ID);

        recipe.setNote(note);
/*
        Category category = new Category();
        category.setId(CAT_ID1);

        recipe.getCategories().add(category);*/

        Ingredient ingredient = new Ingredient();
        ingredient.setId(INGRED1_ID);

        recipe.getIngredients().add(ingredient);

        //when
        RecipeCommand command = converter.convert(recipe);

        //then
        assertNotNull(command);
        assertEquals(RECIPEID, command.getId());
        assertEquals(COOKTIME, command.getCookTime());
        assertEquals(PREPTIME, command.getPrepTime());
        assertEquals(DESCRIPTION, command.getDescription());
        assertEquals(DIFFICULTY, command.getDifficulty());
        assertEquals(DIRECTIONS, command.getDirections());
       /* assertEquals(SERVINGS, command.getServings());
        assertEquals(SOURCE, command.getSource());*/
        assertEquals(URL, command.getUrl());
        assertEquals(NOTES_ID, command.getNote().getId());
        //assertEquals(1, command.getCategories().size());
        assertEquals(1, command.getIngredients().size());

    }
}