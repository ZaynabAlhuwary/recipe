package recipe.repositories;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import recipe.domain.Recipe;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RecipeRepositoryTest {

    @Autowired
    private RecipeRepository recipeRepository;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void shouldReturnRecipeDesc(){
        String desc = "The BEST guacamole! So easy to make with ripe avocados!";
        Recipe recipe = new Recipe();
        recipe.setId(3l);
        recipe.setDescription(desc);
        recipeRepository.save(recipe);

        assertEquals(desc,recipeRepository.findById(1l).get().getDescription());
    }
}