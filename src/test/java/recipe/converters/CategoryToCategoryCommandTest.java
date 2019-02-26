/*
 *  Created By  Zaynab Osama ,  On 2/24/19 12:58 AM
 *
 */

package recipe.converters;

import org.junit.Before;
import org.junit.Test;
import recipe.commands.CategoryCommand;
import recipe.domain.Category;

import static org.junit.Assert.*;

public class CategoryToCategoryCommandTest {

    public static final Long ID =  Long.valueOf(1);
    public static final String DESCRIPTION = "Egyption";

    CategoryToCategoryCommand catgoryConvertor;

    @Before
    public void setUp() throws Exception {
        catgoryConvertor = new CategoryToCategoryCommand();
    }
    @Test
    public void testNullUOMCommand() throws Exception {
        assertNull(catgoryConvertor.convert(null));
    }

    @Test
    public void testEmptyUnitOfMeasureObject() throws Exception {
        assertNotNull(catgoryConvertor.convert(new Category()));
    }

    @Test
    public void convert() {
        //given
        Category command = new Category();
        command.setId(ID);
        command.setDescription(DESCRIPTION);

        //when
        CategoryCommand categoryCommand =  catgoryConvertor.convert(command);

        //then
        assertNotNull(categoryCommand);
        assertEquals(ID, categoryCommand.getId());
        assertEquals(DESCRIPTION, categoryCommand.getDescription());

    }
}