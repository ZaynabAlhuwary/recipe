package recipe.converters.mongo;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import recipe.commands.mongo.M_IngredientCmd;
import recipe.domain.mongo.M_Ingredient;

@Component
public class M_IngredientToIngredientCmd implements Converter<M_Ingredient, M_IngredientCmd> {

    private M_UOMToUOMCmd toUOMCmd;

    public M_IngredientToIngredientCmd(M_UOMToUOMCmd toUOMCmd) {
        this.toUOMCmd = toUOMCmd;
    }

    @Synchronized
    @Nullable
    @Override
    public M_IngredientCmd convert(M_Ingredient mIngredient) {

        if (null == mIngredient) {
            return null;
        }

        M_IngredientCmd cmd = new M_IngredientCmd();
        cmd.setId(mIngredient.getId());
        cmd.setDescription(mIngredient.getDescription());
        cmd.setAmount(mIngredient.getAmount());
        cmd.setUnitOfMeasure(this.toUOMCmd.convert(mIngredient.getUnitOfMeasure()));
        return  cmd;
    }
}
