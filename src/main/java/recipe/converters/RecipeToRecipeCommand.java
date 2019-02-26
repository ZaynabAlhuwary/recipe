/*
 *  Created By  Zaynab Osama ,  On 2/24/19 2:35 AM
 *
 */

package recipe.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import recipe.commands.RecipeCommand;
import recipe.domain.Category;
import recipe.domain.Ingredient;
import recipe.domain.Recipe;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

    private final CategoryToCategoryCommand categoryConveter;
    private final IngredientToIngredientCommand ingredientConverter;
    private final NotesToNoteCommand notesConverter;

    public RecipeToRecipeCommand(CategoryToCategoryCommand categoryConveter, IngredientToIngredientCommand ingredientConverter, NotesToNoteCommand notesConverter) {
        this.categoryConveter = categoryConveter;
        this.ingredientConverter = ingredientConverter;
        this.notesConverter = notesConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe recipe) {
       if(null == recipe){
           return null;
       }

       final RecipeCommand recipeCommand = new RecipeCommand();
       recipeCommand.setId(recipe.getId());
       recipeCommand.setDescription(recipe.getDescription());
       recipeCommand.setDirections(recipe.getDirections());
       recipeCommand.setDifficulty(recipe.getDifficulty());
       recipeCommand.setCategoryCommand(categoryConveter.convert(recipe.getCategory()));
       recipeCommand.setFlag(recipe.isFlag());
       if(recipe.getIngredients() !=null  && recipe.getIngredients().size()>0){

           recipe.getIngredients().forEach((Ingredient ingredient)-> recipeCommand.getIngredients()
                   .add(ingredientConverter.convert(ingredient)));
       }
       recipeCommand.setCookTime(recipe.getCookTime());
       recipeCommand.setPrepTime(recipe.getPrepTime());
       /*recipeCommand.setServings(recipe.getServings());
       recipeCommand.setSource(recipe.getSource());*/
       recipeCommand.setUrl(recipe.getUrl());
       recipeCommand.setNote(notesConverter.convert(recipe.getNote()));

       return recipeCommand;
    }
}
