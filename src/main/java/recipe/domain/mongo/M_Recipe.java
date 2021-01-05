package recipe.domain.mongo;import lombok.Getter;import lombok.NoArgsConstructor;import lombok.Setter;import org.springframework.data.mongodb.core.mapping.DBRef;import org.springframework.data.mongodb.core.mapping.Document;import recipe.domain.Difficulty;import javax.persistence.EnumType;import javax.persistence.Enumerated;import java.util.HashSet;import java.util.Set;@Getter@Setter@NoArgsConstructor@Documentpublic class M_Recipe extends Base {    private String description;    private Integer cookTime;    private Integer prepTime;    private String url;    private String directions;    private boolean flag;    private Set<M_Ingredient> ingredients = new HashSet<M_Ingredient>();    private M_Note note;    @Enumerated(EnumType.STRING)    private Difficulty difficulty;    @DBRef    private M_Category category;    public void setMNote(M_Note MNote) {        if (MNote != null) {            this.note = MNote;        }    }    public void addIngredient(M_Ingredient MIngredient) {        this.ingredients.add(MIngredient);    }    public boolean isFlag() {        return flag;    }    public void setFlag(boolean flag) {        this.flag = flag;    }}