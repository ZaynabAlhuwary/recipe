package recipe.repositories;import org.junit.jupiter.api.BeforeEach;import org.junit.jupiter.api.Test;import org.junit.jupiter.api.extension.ExtendWith;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;import org.springframework.test.context.junit.jupiter.SpringExtension;import recipe.domain.Ingredient;import recipe.domain.Recipe;import recipe.domain.UnitOfMeasure;import static org.junit.jupiter.api.Assertions.assertEquals;@ExtendWith(SpringExtension.class)@DataJpaTestclass IngredientRepositoryIT {    @Autowired    RecipeRepository recipeRepository;    @Autowired    IngredientRepository ingredientRepository;    private final Long idValue = 1l;    private final Long uomId = 4l;    private Recipe perfectGuacamole = new Recipe();    private Ingredient ingredient = new Ingredient();    private UnitOfMeasure unitOfMeasure = new UnitOfMeasure();    private final String uom = "Cup";    @BeforeEach    void prepareIngredientData() {        perfectGuacamole.setId(idValue);        unitOfMeasure.setId(uomId);        unitOfMeasure.setUom(uom);        ingredient.setId(idValue);        ingredient.setRecipe(perfectGuacamole);        ingredient.setUnitOfMeasure(unitOfMeasure);        perfectGuacamole.addIngredient(ingredient);        this.recipeRepository.save(perfectGuacamole);      //  this.ingredientRepository.save(ingredient);    }    @Test    void getIngredientById() {        Ingredient ingredient = this.ingredientRepository.findById(idValue).get();        assertEquals(idValue, (ingredient).getId());        assertEquals(perfectGuacamole, (ingredient).getRecipe());        final String ingredientUOM = ((ingredient).getUnitOfMeasure()).getUom();        assertEquals(uom, ingredientUOM);    }}