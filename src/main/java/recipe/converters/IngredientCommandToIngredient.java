/*
 *  Created By  Zaynab Osama ,  On 2/24/19 1:46 AM
 *
 */

package recipe.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import recipe.commands.IngredientCommand;
import recipe.domain.Ingredient;

@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {

    private UnitOfMeasureCommandToUnitOfMeasure uomConverter;

    public IngredientCommandToIngredient(UnitOfMeasureCommandToUnitOfMeasure uomConverter) {
        this.uomConverter = uomConverter;
    }

    @Nullable
    @Override
    public Ingredient convert(IngredientCommand ingredientCmd) {
        if(null==ingredientCmd){
            return null;
        }
        final  Ingredient ingredient= new Ingredient();
        ingredient.setId(ingredientCmd.getId());
        ingredient.setDescription(ingredientCmd.getDescription());
        ingredient.setUnitOfMeasure(uomConverter.convert(ingredientCmd.getUnitOfMeasure()));
        ingredient.setAmount(ingredientCmd.getAmount());

        return ingredient;
    }
}
