/*
 *  Created By  Zaynab Osama ,  On 2/23/19 2:43 PM
 *
 */

package recipe.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Data
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class Note extends Base {

    @Lob
    private String recipeNotes;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Recipe recipe;

}
