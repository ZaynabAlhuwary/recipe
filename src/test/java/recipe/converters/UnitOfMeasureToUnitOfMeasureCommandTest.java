/*
 *  Created By  Zaynab Osama ,  On 2/24/19 1:19 AM
 *
 */

package recipe.converters;

import org.junit.Before;
import org.junit.Test;
import recipe.commands.UnitOfMeasureCommand;
import recipe.domain.UnitOfMeasure;

import static org.junit.Assert.*;

public class UnitOfMeasureToUnitOfMeasureCommandTest {

    public static final Long ID =  Long.valueOf(1);
    public static final String DESCRIPTION = "Ounce";


    UnitOfMeasureToUnitOfMeasureCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new UnitOfMeasureToUnitOfMeasureCommand();

    }

    @Test
    public void testNullUOMCommand() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyUnitOfMeasureObject() throws Exception {
        assertNotNull(converter.convert(new UnitOfMeasure()));
    }

    @Test
    public void convert() {
        //given
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(ID);
        uom.setUom(DESCRIPTION);

        //when
        UnitOfMeasureCommand uomCmd = converter.convert(uom);

        //then
        assertNotNull(uomCmd);
        assertEquals(ID, uomCmd.getId());
        assertEquals(DESCRIPTION, uomCmd.getDescription());

    }
}