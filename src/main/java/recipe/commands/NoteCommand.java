/*
 *  Created By  Zaynab Osama ,  On 2/23/19 2:54 PM
 *
 */

package recipe.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class NoteCommand {
    private Long id;
    private String recipeNotes;
}
