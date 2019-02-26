/*
 *  Created By  Zaynab Osama ,  On 2/23/19 2:45 PM
 *
 */

package recipe.services;

import recipe.commands.IngredientCommand;
import recipe.domain.Ingredient;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IngredientService {

    void saveIngredient(Ingredient ingredient);
    Optional<Ingredient> getIngredientById(Long id);
    Set<Ingredient> getIngredient();
    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
    IngredientCommand saveIngredientCommand(IngredientCommand command);
    void deleteById(Long recipeId, Long idToDelete);
}
