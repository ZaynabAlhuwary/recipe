/* *  Created By  Zaynab Osama ,  On 2/23/19 2:43 PM * */package recipe.domain;import lombok.EqualsAndHashCode;import lombok.Getter;import lombok.Setter;import javax.persistence.CascadeType;import javax.persistence.Entity;import javax.persistence.OneToMany;import java.util.Set;/** * @author Zaynab osama */@Getter@Setter@EqualsAndHashCode(of = {"id"}, exclude = {"recipes"})@Entitypublic class Category extends Base {    private String description;    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)    private Set<Recipe> recipes;}