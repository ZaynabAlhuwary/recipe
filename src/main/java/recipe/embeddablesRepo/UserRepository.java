/*
 *  Created By  Zaynab Osama ,  On 2/23/19 2:44 PM
 *
 */

package recipe.embeddablesRepo;

import org.springframework.data.repository.CrudRepository;
import recipe.embeddables.User;

public interface UserRepository extends CrudRepository<User,Long>{

  //  User findByAddress_Country(String country);
}
