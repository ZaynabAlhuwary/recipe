/*
 *  Created By  Zaynab Osama ,  On 2/24/19 1:46 AM
 *
 */

package recipe.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import recipe.commands.IngredientCommand;
import recipe.commands.UnitOfMeasureCommand;
import recipe.domain.Ingredient;
import recipe.services.UnitOfMeasureService;

@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {

    private UnitOfMeasureCommandToUnitOfMeasure uomConverter;
    private UnitOfMeasureService unitOfMeasureService;

    public IngredientCommandToIngredient(UnitOfMeasureCommandToUnitOfMeasure uomConverter, UnitOfMeasureService unitOfMeasureService) {
        this.uomConverter = uomConverter;
        this.unitOfMeasureService = unitOfMeasureService;
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
        UnitOfMeasureCommand unitOfMeasureCommand = (this.unitOfMeasureService.findById(ingredientCmd.getUnitOfMeasureId())).get();
        ingredient.setUnitOfMeasure(uomConverter.convert(unitOfMeasureCommand));
        ingredient.setAmount(ingredientCmd.getAmount());

        return ingredient;
    }
}
