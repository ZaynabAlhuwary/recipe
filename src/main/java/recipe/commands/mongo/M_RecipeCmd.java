package recipe.commands.mongo;

import lombok.Getter;
import lombok.Setter;
import recipe.domain.Difficulty;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class M_RecipeCmd {

    private String id;
    private String description;
    private Integer cookTime;
    private Integer prepTime;
    private String url;
    private String directions;
    private boolean flag;
    private Set<M_IngredientCmd> ingredients = new HashSet<M_IngredientCmd>();
    private M_Note_Cmd note;
    private Difficulty difficulty;
    private M_CategoryCmd category;

    public void setMNote(M_Note_Cmd MNote) {
        if (MNote != null) {
            this.note = MNote;
        }
    }

    public void addIngredient(M_IngredientCmd MIngredient) {
        this.ingredients.add(MIngredient);
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
