package recipe.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class CategoryTest {

    Category category;
    Recipe recipe;
    Recipe secondRecipe;
    Set<Recipe> recipes;

    @Before
    public void setUp() throws Exception {
        category = new Category();
    }

    @Test
    public void getId() throws Exception {

        Long idValue=4l;
        category.setId(idValue);
        assertEquals(idValue,category.getId());
    }

    @Test
    public void getDescription() throws Exception {

        String desc = "new Category";
        category.setDescription(desc);
        assertEquals(desc,category.getDescription());
    }

    @Test
    public void getRecipes() throws Exception {

        recipes = getRecipesObjects();
        category.setRecipes(recipes);
        assertEquals(recipes,category.getRecipes());
    }

    private Set<Recipe> getRecipesObjects(){
        recipe = new Recipe();
        secondRecipe = new Recipe();
        recipes = new HashSet<Recipe>();
        recipe.setId(1l);
        recipe.setDescription("first Recipe");
        secondRecipe.setId(2l);
        secondRecipe.setDescription("Second Recipe");

        recipes.add(recipe);
        recipes.add(secondRecipe);
        return recipes;


    }
}