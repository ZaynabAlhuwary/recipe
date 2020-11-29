/*
 *  Created By  Zaynab Osama ,  On 2/23/19 2:44 PM
 *
 */

package recipe.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import recipe.domain.Recipe;

import java.util.Optional;


@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long> {

    Optional<Recipe> findBydescription(String desc);

   /* @Query(value = "Dele uUpload up INNER JOIN (SELECT u.* FROM uUpload u INNER JOIN usUser us ON u.uUser = us.usId WHERE :status IS NULL OR u.uStatus = :status AND us.usEmail = :email LIMIT :limit)  as sub on up.uId=sub.uId SET up.uIsDeleted = TRUE,up.uDeletedDate = NOW()",nativeQuery = true)
    @Modifying
    @Transactional
    public void deleteRecipeByChildsById(Long recipeId);*/
}
