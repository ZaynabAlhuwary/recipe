
/*
 *  Created By  Zaynab Osama ,  On 2/23/19 2:43 PM
 *
 */
package recipe.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Recipe extends Base {

    private String description;
    private Integer cookTime;
    private Integer prepTime;
    /*private Integer servings;
    private String source;*/
    private String url;
    private String directions;


    @OneToMany(fetch = FetchType.EAGER,cascade =  CascadeType.ALL,mappedBy = "recipe")
    private Set<Ingredient> ingredients = new HashSet<Ingredient>();

    @OneToOne(cascade = CascadeType.ALL)
    private Note note;

    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    /*@ManyToMany
    @JoinTable(name = "recipe_category",
               joinColumns = @JoinColumn(name = "recipe_id"),
               inverseJoinColumns = @JoinColumn(name = "category_id"))*/
    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category; //= new HashSet<>();

    public void setNote(Note note) {
        if (note != null) {
            this.note = note;
            note.setRecipe(this);
        }
    }


    public void addIngredient(Ingredient ingredient){
        this.ingredients.add(ingredient);
        ingredient.setRecipe(this);
    }

}
