/*
 *  Created By  Zaynab Osama ,  On 2/24/19 1:15 AM
 *
 */

package recipe.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import recipe.commands.UnitOfMeasureCommand;
import recipe.domain.UnitOfMeasure;


@Component
public class UnitOfMeasureToUnitOfMeasureCommand implements Converter<UnitOfMeasure,UnitOfMeasureCommand>{
    @Override
    public UnitOfMeasureCommand convert(UnitOfMeasure uom) {
        if(null == uom){
            return null;
        }

        final UnitOfMeasureCommand uomCmd = new UnitOfMeasureCommand();
        uomCmd.setId(uom.getId());
        uomCmd.setDescription(uom.getUom());

        return uomCmd;
    }
}
