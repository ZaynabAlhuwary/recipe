/*
 *  Created By  Zaynab Osama ,  On 2/24/19 1:09 AM
 *
 */

package recipe.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import recipe.commands.IngredientCommand;
import recipe.domain.Ingredient;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredient,IngredientCommand> {

    private UnitOfMeasureToUnitOfMeasureCommand  unitOfMeasureCmd;

    public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand unitOfMeasure) {
        this.unitOfMeasureCmd = unitOfMeasure;
    }

    @Nullable
    @Override
    public IngredientCommand convert(Ingredient ingredient) {
        if(null== ingredient){
            return null;
        }

        final IngredientCommand ingredientCmd = new IngredientCommand();
        ingredientCmd.setId(ingredient.getId());
        ingredientCmd.setDescription(ingredient.getDescription());
        ingredientCmd.setAmount(ingredient.getAmount());
        ingredientCmd.setUnitOfMeasure(unitOfMeasureCmd.convert(ingredient.getUnitOfMeasure()));

        return ingredientCmd;
    }
}
