/*
 *  Created By  Zaynab Osama ,  On 2/23/19 3:31 PM
 *
 */

package recipe.converters;

import org.junit.Before;
import org.junit.Test;
import recipe.commands.UnitOfMeasureCommand;
import recipe.domain.UnitOfMeasure;

import static org.junit.Assert.*;

public class UnitOfMeasureCommandToUnitOfMeasureTest {

    public static final Long ID =  Long.valueOf(1);
    public static final String DESCRIPTION = "Ounce";


    UnitOfMeasureCommandToUnitOfMeasure converter;

    @Before
    public void setUp() throws Exception {
        converter = new UnitOfMeasureCommandToUnitOfMeasure();

    }

    @Test
    public void testNullUOMCommand() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyUnitOfMeasureObject() throws Exception {
        assertNotNull(converter.convert(new UnitOfMeasureCommand()));
    }

    @Test
    public void convert() {
        //given
        UnitOfMeasureCommand command = new UnitOfMeasureCommand();
        command.setId(ID);
        command.setDescription(DESCRIPTION);

        //when
        UnitOfMeasure uom = converter.convert(command);

        //then
        assertNotNull(uom);
        assertEquals(ID, uom.getId());
        assertEquals(DESCRIPTION, uom.getUom());

    }
}