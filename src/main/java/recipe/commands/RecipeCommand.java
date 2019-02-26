/*
 *  Created By  Zaynab Osama ,  On 2/23/19 2:50 PM
 *
 */

package recipe.commands;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import recipe.domain.Difficulty;

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
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    /*private Integer servings;
    private String source;*/
    private String url;
    private String directions;
    private Set<IngredientCommand> ingredients = new HashSet<IngredientCommand>();
    private Difficulty difficulty;
    private NoteCommand note;
    private CategoryCommand categoryCommand;
    private String commndCat;
    private Set<CategoryCommand> categories = new HashSet<>();
    private String[] selectedCategories;

    public CategoryCommand getCategoryCommand() {
        if(null != this.commndCat){
            categoryCommand = new CategoryCommand();
            String[] cats = this.commndCat.split(",");
            categoryCommand.setId(Long.valueOf(cats[0]));
            categoryCommand.setDescription(cats[1]);
        }
        return categoryCommand;
    }

    /* public Set<CategoryCommand> getSelectedCategory(){
        CategoryCommand cmd ;
       *//* if(null != this.selectedCategories){
            for(String commanCategoryId: selectedCategories){
                cmd = new CategoryCommand();
                cmd.setId(Long.parseLong(commanCategoryId));
                categories.add(cmd );
            }
        }*//*
       // log.info("Selected Categories are " +selectedCategories);
        if(null != this.selectedCategories) {
            for (int i = 0; i < selectedCategories.length; i++) {
                cmd = new CategoryCommand();
                cmd.setId(Long.parseLong(selectedCategories[i]));
                categories.add(cmd);
            }
        }
      return categories;
    }*/


}