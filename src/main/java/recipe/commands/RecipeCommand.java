/*
 *  Created By  Zaynab Osama ,  On 2/23/19 2:50 PM
 *
 */

package recipe.commands;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.URL;
import recipe.domain.Difficulty;

import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

/**
 * RecipeCommand class
 */

@Getter
@Setter
@NoArgsConstructor
@Slf4j
public class RecipeCommand {
    private Long id;
    @NotBlank
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    @URL
    private String url;
    private String directions;
    private Set<IngredientCommand> ingredients = new HashSet<IngredientCommand>();
    private Difficulty difficulty;
    private NoteCommand note;
    private CategoryCommand categoryCommand;
    private String commndCat;
    private Set<CategoryCommand> categories = new HashSet<>();

    private boolean flag;

    public CategoryCommand getCategoryCommand() {
        if(null != this.commndCat){
            categoryCommand = new CategoryCommand();
            String[] cats = this.commndCat.split(",");
            categoryCommand.setId(Long.valueOf(cats[0]));
            categoryCommand.setDescription(cats[1]);
        }
        return categoryCommand;
    }


}