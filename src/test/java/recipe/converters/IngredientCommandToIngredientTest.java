/*
 *  Created By  Zaynab Osama ,  On 2/24/19 1:57 AM
 *
 */

package recipe.converters;

import org.junit.Before;
import org.junit.Test;
import recipe.commands.IngredientCommand;
import recipe.commands.UnitOfMeasureCommand;
import recipe.domain.Ingredient;
import recipe.domain.Recipe;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientCommandToIngredientTest {

    public static final Recipe RECIPE = new Recipe();
    public static final BigDecimal AMOUNT = new BigDecimal("5");
    public static final String DESCRIPTION = "Guacamole";
    public static final Long UOM_ID = Long.valueOf(2L);
    public static final Long ID_VALUE = Long.valueOf(1L);


    IngredientCommandToIngredient converter;

    @Before
    public void setUp() throws Exception {
        converter = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @Test
    public void testNullConvert() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new IngredientCommand()));
    }

    @Test
    public void testConvertNullUOM() throws Exception {
        //given
        IngredientCommand ingredientCmd = new IngredientCommand();
        ingredientCmd.setId(ID_VALUE);
        ingredientCmd.setAmount(AMOUNT);
        ingredientCmd.setDescription(DESCRIPTION);
        ingredientCmd.setUnitOfMeasure(null);
        //when
        Ingredient ingredient = converter.convert(ingredientCmd);
        //then
        assertNull(ingredient.getUnitOfMeasure());
        assertEquals(ID_VALUE, ingredient.getId());
        assertEquals(AMOUNT, ingredient.getAmount());
        assertEquals(DESCRIPTION, ingredient.getDescription());
    }

    @Test
    public void testConvertWithUom() throws Exception {
        //given
        IngredientCommand ingredientCmd = new IngredientCommand();
        ingredientCmd.setId(ID_VALUE);
        ingredientCmd.setAmount(AMOUNT);
        ingredientCmd.setDescription(DESCRIPTION);

        UnitOfMeasureCommand uomCmd = new UnitOfMeasureCommand();
        uomCmd.setId(UOM_ID);

        ingredientCmd.setUnitOfMeasure(uomCmd);
        //when
        Ingredient ingredient = converter.convert(ingredientCmd);
        //then
        assertEquals(ID_VALUE, ingredient.getId());
        assertNotNull(ingredient.getUnitOfMeasure());
        assertEquals(UOM_ID, ingredient.getUnitOfMeasure().getId());
        assertEquals(AMOUNT, ingredient.getAmount());
        assertEquals(DESCRIPTION, ingredient.getDescription());
    }
}