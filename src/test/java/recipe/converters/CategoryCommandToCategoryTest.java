/*
 *  Created By  Zaynab Osama ,  On 2/24/19 12:48 AM
 *
 */

package recipe.converters;

import org.junit.Before;
import org.junit.Test;
import recipe.commands.CategoryCommand;
import recipe.domain.Category;

import static org.junit.Assert.*;

public class CategoryCommandToCategoryTest {

    public static final Long ID =  Long.valueOf(1);
    public static final String DESCRIPTION = "Egyption";

    CategoryCommandToCategory catgoryCmdConvertor;

    @Before
    public void setUp() throws Exception {
        catgoryCmdConvertor = new CategoryCommandToCategory();
    }
    @Test
    public void testNullUOMCommand() throws Exception {
        assertNull(catgoryCmdConvertor.convert(null));
    }

    @Test
    public void testEmptyUnitOfMeasureObject() throws Exception {
        assertNotNull(catgoryCmdConvertor.convert(new CategoryCommand()));
    }

    @Test
    public void convert() {
        //given
        CategoryCommand command = new CategoryCommand();
        command.setId(ID);
        command.setDescription(DESCRIPTION);

        //when
        Category category =  catgoryCmdConvertor.convert(command);

        //then
        assertNotNull(category);
        assertEquals(ID, category.getId());
        assertEquals(DESCRIPTION, category.getDescription());

    }
}