/*
 *  Created By  Zaynab Osama ,  On 2/23/19 2:44 PM
 *
 */

package recipe.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import recipe.domain.Category;

import java.util.Optional;

@Repository
public interface CategoryRepository extends CrudRepository<Category,Long> {
    Optional<Category>  findByDescription(String desc);
}
