/*
 *  Created By  Zaynab Osama ,  On 2/23/19 2:45 PM
 *
 */

package recipe.services;

import recipe.commands.RecipeCommand;
import recipe.domain.Category;
import recipe.domain.Ingredient;
import recipe.domain.Recipe;

import java.util.Optional;
import java.util.Set;

public interface RecipeService {

    void saveRecipe(Recipe recipe);
    Set<Recipe> getRecipes();
    Recipe findRecipeById(Long id);
    Recipe findByDesc(String desc);
    RecipeCommand saveRecipeCommand(RecipeCommand command);
    RecipeCommand findCommandById(Long id);
    void deleteById(Long recipeId);
}
