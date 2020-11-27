/*
 *  Created By  Zaynab Osama ,  On 2/23/19 2:43 PM
 *
 */

package recipe.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

/**
 * @author Zaynab osama
 */
@Data
@EqualsAndHashCode(exclude = {"recipes"})
@Entity
public class Category extends Base {


    private String description;

    @OneToMany(mappedBy = "category")
    private Set<Recipe> recipes;


}
