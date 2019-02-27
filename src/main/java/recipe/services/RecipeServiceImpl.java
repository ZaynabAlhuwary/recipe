/*
 *  Created By  Zaynab Osama ,  On 2/23/19 2:45 PM
 *
 */

package recipe.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import recipe.commands.RecipeCommand;
import recipe.converters.CategoryCommandToCategory;
import recipe.converters.RecipeCommandToRecipe;
import recipe.converters.RecipeToRecipeCommand;
import recipe.domain.Category;
import recipe.domain.Ingredient;
import recipe.domain.Recipe;
import recipe.repositories.RecipeRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * This Class Is Implementation For Recipe Services.
 */
@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService{

    private RecipeRepository recipeRepository;
    private RecipeToRecipeCommand recipeToRecipeCommand;
    private RecipeCommandToRecipe recipeCommandToRecipe;
    private CategoryCommandToCategory categoryCommandToCategory;

    /**
     * @param recipeRepository :     RecipeRepository
     * @param recipeToRecipeCommand: RecipeToRecipeCommand
     * @param recipeCommandToRecipe: RecipeCommandToRecipe
     *
     * Use All Of These To Construct RecipeServiceImpl Object.
     */
    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeToRecipeCommand recipeToRecipeCommand, RecipeCommandToRecipe recipeCommandToRecipe, CategoryCommandToCategory categoryCommandToCategory) {
        this.recipeRepository = recipeRepository;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.categoryCommandToCategory = categoryCommandToCategory;
    }

    /**
     * @param recipe : Save The Passed Recipe.
     */
    @Override
    public void saveRecipe(Recipe recipe) {
            recipeRepository.save(recipe);
    }

    /**
     * @return: All Found Recipe In DB.
     */
    @Override
    public Set<Recipe> getRecipes() {

        Set<Recipe> recipes = new HashSet<Recipe>();
        recipeRepository.findAll().iterator().forEachRemaining(recipes::add);
        return recipes;

    }


    /**
     * @param id: Recipe ID To Find.
     * @return: Matched Recipe.
     */
    @Override
    public Recipe findRecipeById(Long id) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);
        if(!recipeOptional.isPresent()){
            throw new RuntimeException("Recipe Not Found By This Id: "+id);
        }

        return recipeOptional.get();
    }

    /**
     * @param desc: Description For The Recipe To Find.
     * @return: Matched Recipe.
     */
    @Override
    public Recipe findByDesc(String desc) {
        return recipeRepository.findBydescription(desc).get();
    }


    /**
     * @param command: RecipeCommand Object To Save In DB
     * @return : Saved RecipeCommand Object
     */
    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand command) {

        Recipe detachedRecipe = this.recipeCommandToRecipe.convert(command);
        Recipe savedRecipe    = this.recipeRepository.save(detachedRecipe);
        savedRecipe.setCategory(categoryCommandToCategory.convert(command.getCategoryCommand()));
        return this.recipeToRecipeCommand.convert(savedRecipe);

    }

    /**
     * @param id: Recipe ID To Find
     * @return: RecipeCommand Equivalent Object
     */
    @Override
    @Transactional
    public RecipeCommand findCommandById(Long id) {
        return recipeToRecipeCommand.convert(findRecipeById(id));
    }

    /**
     * @param recipeId : Id To Delete The Recipe.
     */
    @Override
    public void deleteById(Long recipeId) {
        recipeRepository.deleteById(recipeId);
    }
}
