/*
 *  Created By  Zaynab Osama ,  On 2/23/19 2:44 PM
 *
 */

package recipe.repositories;

import org.springframework.data.repository.CrudRepository;
import recipe.domain.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient,Long> {
}
