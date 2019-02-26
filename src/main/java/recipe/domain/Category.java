/*
 *  Created By  Zaynab Osama ,  On 2/23/19 2:43 PM
 *
 */

package recipe.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author  Zaynab osama
 */
@Getter
@Setter
@EqualsAndHashCode(exclude = {"recipes"})
@Entity
public class Category extends Base {


    private String description;

    /*@ManyToMany(mappedBy = "categories")*/
    @OneToMany(mappedBy = "category")
    private Set<Recipe> recipes ;


}
