/*
 *  Created By  Zaynab Osama ,  On 2/23/19 2:44 PM
 *
 */

package recipe.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import recipe.domain.UnitOfMeasure;

import java.util.Optional;

@Repository
public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure,Long> {

    Optional<UnitOfMeasure> findByUom(String uom);
    Optional<UnitOfMeasure> findById(Long Id);
}
