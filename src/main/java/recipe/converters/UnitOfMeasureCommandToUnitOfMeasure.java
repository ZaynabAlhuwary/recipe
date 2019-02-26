/*
 *  Created By  Zaynab Osama ,  On 2/23/19 3:29 PM
 *
 */

package recipe.converters;

import lombok.Synchronized;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import recipe.commands.UnitOfMeasureCommand;
import java.lang.annotation.Annotation;
import org.springframework.core.convert.converter.Converter;
import recipe.domain.UnitOfMeasure;

@Component
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand,UnitOfMeasure> {

    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasure convert(UnitOfMeasureCommand uomCommand) {
        if(null == uomCommand) {
            return null;
        }

        // final to be immutable obj
        final UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(uomCommand.getId());
        uom.setUom(uomCommand.getDescription());

        return uom;
    }
}
