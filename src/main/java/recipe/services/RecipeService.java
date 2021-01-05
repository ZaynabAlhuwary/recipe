/*
 *  Created By  Zaynab Osama ,  On 2/23/19 2:45 PM
 *
 */

package recipe.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import recipe.commands.RecipeCommand;
import recipe.domain.Recipe;

public interface RecipeService {

    void saveRecipe(Recipe recipe);

    Page<Recipe> getRecipes(Pageable pageable);

    Recipe findRecipeById(Long id);

    Recipe findByDesc(String desc);

    RecipeCommand saveRecipeCommand(RecipeCommand command);

    RecipeCommand findCommandById(Long id);

    void deleteById(Long recipeId);
}
