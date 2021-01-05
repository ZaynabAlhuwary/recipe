/* *  Created By  Zaynab Osama ,  On 2/23/19 2:45 PM * */package recipe.services;import lombok.extern.slf4j.Slf4j;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.data.domain.Page;import org.springframework.data.domain.Pageable;import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;import recipe.commands.RecipeCommand;import recipe.converters.CategoryCommandToCategory;import recipe.converters.RecipeCommandToRecipe;import recipe.converters.RecipeToRecipeCommand;import recipe.domain.Recipe;import recipe.exceptions.NotFoundException;import recipe.repositories.RecipeRepository;import java.util.Optional;/** * This Class Is Implementation For Recipe Services. */@Slf4j@Servicepublic class RecipeServiceImpl implements RecipeService {    private RecipeRepository recipeRepository;    private RecipeToRecipeCommand recipeToRecipeCommand;    private RecipeCommandToRecipe recipeCommandToRecipe;    private CategoryCommandToCategory categoryCommandToCategory;    /**     * @param recipeRepository       :     RecipeRepository     * @param recipeToRecipeCommand: RecipeToRecipeCommand     * @param recipeCommandToRecipe: RecipeCommandToRecipe     *                               <p>     *                               Use All Of These To Construct RecipeServiceImpl Object.     */    @Autowired    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeToRecipeCommand recipeToRecipeCommand, RecipeCommandToRecipe recipeCommandToRecipe, CategoryCommandToCategory categoryCommandToCategory) {        this.recipeRepository = recipeRepository;        this.recipeToRecipeCommand = recipeToRecipeCommand;        this.recipeCommandToRecipe = recipeCommandToRecipe;        this.categoryCommandToCategory = categoryCommandToCategory;    }    /**     * @param recipe : Save The Passed Recipe.     */    @Override    public void saveRecipe(Recipe recipe) {        recipeRepository.save(recipe);    }    /**     * @return: All Found Recipe In DB.     */    @Override    public Page<Recipe> getRecipes(Pageable pageable) {        return recipeRepository.findAll(pageable);    }    /**     * @param id: Recipe ID To Find.     * @return: Matched Recipe.     */    @Override    public Recipe findRecipeById(Long id) {        Optional<Recipe> recipeOptional = recipeRepository.findById(id);        if (!recipeOptional.isPresent()) {            final String errorMsg = String.format("%s%s", "M_Recipe Not Found By This Id: ", id.toString());            log.error(errorMsg);            throw new NotFoundException(errorMsg);        }        return recipeOptional.get();    }    /**     * @param desc: Description For The Recipe To Find.     * @return: Matched Recipe.     */    @Override    public Recipe findByDesc(String desc) {        Optional<Recipe> optionalRecipe = null;        optionalRecipe = recipeRepository.findBydescription(desc);        if (!optionalRecipe.isPresent()) {            final String errorMsg = String.format("%s%s", "No Recipe With This description", desc);            log.error(errorMsg);            throw new NotFoundException(errorMsg);        }        return optionalRecipe.get();    }    /**     * @param command: RecipeCommand Object To Save In DB     * @return : Saved RecipeCommand Object     */    @Override    @Transactional    public RecipeCommand saveRecipeCommand(RecipeCommand command) {        Recipe detachedRecipe = this.recipeCommandToRecipe.convert(command);        Recipe savedRecipe = this.recipeRepository.save(detachedRecipe);        //   savedRecipe.setCategory(categoryCommandToCategory.convert(command.getCategoryCommand()));        RecipeCommand recipeCommand = this.recipeToRecipeCommand.convert(savedRecipe);        return recipeCommand;    }    /**     * @param id: Recipe ID To Find     * @return: RecipeCommand Equivalent Object     */    @Override    @Transactional    public RecipeCommand findCommandById(Long id) {        return recipeToRecipeCommand.convert(findRecipeById(id));    }    /**     * @param recipeId : Id To Delete The Recipe.     */    @Override    public void deleteById(Long recipeId) {        recipeRepository.deleteById(recipeId);    }}