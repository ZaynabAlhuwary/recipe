/*
 *  Created By  Zaynab Osama ,  On 2/24/19 4:25 AM
 *
 */

package recipe.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import recipe.commands.CategoryCommand;
import recipe.commands.RecipeCommand;
import recipe.domain.Category;
import recipe.domain.Recipe;

import java.util.HashSet;
import java.util.Set;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand,Recipe> {

    private CategoryCommandToCategory categoryConverter;
    private final IngredientCommandToIngredient ingredientConverter;
    private final NoteCommandToNote notesConverter;

    public RecipeCommandToRecipe(CategoryCommandToCategory categoryConverter, IngredientCommandToIngredient ingredientConverter, NoteCommandToNote notesConverter) {
        this.categoryConverter = categoryConverter;
        this.ingredientConverter = ingredientConverter;
        this.notesConverter = notesConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand recipeCmd) {
        if(null==recipeCmd){
            return null;
        }

        final Recipe recipe = new Recipe();
        recipe.setId(recipeCmd.getId());
        recipe.setCookTime(recipeCmd.getCookTime());
        recipe.setPrepTime(recipeCmd.getPrepTime());
        recipe.setDescription(recipeCmd.getDescription());
        recipe.setDifficulty(recipeCmd.getDifficulty());
        recipe.setDirections(recipeCmd.getDirections());
       /* recipe.setServings(recipeCmd.getServings());
        recipe.setSource(recipeCmd.getSource());*/
        recipe.setUrl(recipeCmd.getUrl());
        recipe.setNote(notesConverter.convert(recipeCmd.getNote()));
        recipe.setCategory(categoryConverter.convert(recipeCmd.getCategoryCommand()));

       // if (recipeCmd.getCategories() != null && recipeCmd.getCategories().size() > 0){
       /* if(null != recipeCmd.getSelectedCategories()){
            recipeCmd.setCategories(recipeCmd.getSelectedCategory());
            recipeCmd.getCategories()
                    .forEach( category -> recipe.getCategories().add(categoryConverter.convert(category)));
        }*/

        if (recipeCmd.getIngredients() != null && recipeCmd.getIngredients().size() > 0){
            recipeCmd.getIngredients()
                    .forEach(ingredient -> recipe.getIngredients().add(ingredientConverter.convert(ingredient)));
        }

        return recipe;
    }

}
