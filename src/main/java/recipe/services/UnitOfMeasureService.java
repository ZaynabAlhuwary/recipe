/*
 *  Created By  Zaynab Osama ,  On 2/23/19 2:45 PM
 *
 */

package recipe.services;

import recipe.commands.UnitOfMeasureCommand;
import recipe.domain.UnitOfMeasure;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UnitOfMeasureService {

    public void saveUnit(UnitOfMeasure unitOfMeasure);
    Optional<UnitOfMeasure> findByUom(String uom);
    Optional<UnitOfMeasureCommand> findById(Long id);
    Set<UnitOfMeasure> findAllUnitOfMeasures();
    Set<UnitOfMeasureCommand> getAllCommandUoms();
}
