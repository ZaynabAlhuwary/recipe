package recipe.domain;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class RecipeTest {
    private Recipe recipe;
    private Ingredient ingredient;
    private Note note;
    private Category category;

    @Before
    public void setUp() throws Exception {
        recipe = new Recipe();
    }

    @Test
    public void addIngredient() throws Exception {
        ingredient = new Ingredient("new Ingredienr", BigDecimal.valueOf(2),new UnitOfMeasure());
        recipe.addIngredient(ingredient);
        assertTrue(recipe.getIngredients().contains(ingredient));
    }

    @Test
    public void getId() throws Exception {
        Long idValue =4L;
        recipe.setId(idValue);
        assertEquals(idValue,recipe.getId());
    }

    @Test
    public void getDescription() throws Exception {

        String desc = "recipe description just for test";
        recipe.setDescription(desc);
        assertEquals(desc,recipe.getDescription());
    }

    @Test
    public void getIngredients() throws Exception {

        Set<Ingredient> ingredientSet = new HashSet<Ingredient>();
        ingredientSet.add(new Ingredient());
        ingredientSet.add(new Ingredient("test",BigDecimal.valueOf(3),new UnitOfMeasure()));
        recipe.setIngredients(ingredientSet);
        assertEquals(2,recipe.getIngredients().size());
    }

    @Test
    public void getNote() throws Exception {

        note = new Note();
        note.setRecipe(recipe);
        recipe.setNote(note);

        assertEquals(note,recipe.getNote());

    }

    @Test
    public void getDiffeculty() throws Exception {
        recipe.setDifficulty(Difficulty.EASY);
        assertEquals(Difficulty.EASY,recipe.getDifficulty());

    }

    @Test
    public void getCategories() throws Exception {

       /* Set<Category> categories = new HashSet<Category>();
        categories.add(new Category());*/
        recipe.setCategory(new Category());
        Set<Recipe> recipes = new HashSet<Recipe>();
        recipes.add(recipe);
        category = new Category();
        category.setRecipes(recipes);
       // assertEquals(1,recipe.getCategories().size());
        assertTrue(category.getRecipes().contains(recipe));

    }

}