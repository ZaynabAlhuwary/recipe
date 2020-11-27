/* * Created By  Zaynab Osama , On:  2/24/19 9:00 PM */package recipe.controllers;import lombok.extern.slf4j.Slf4j;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Controller;import org.springframework.ui.Model;import org.springframework.web.bind.annotation.GetMapping;import org.springframework.web.bind.annotation.ModelAttribute;import org.springframework.web.bind.annotation.PathVariable;import org.springframework.web.bind.annotation.PostMapping;import recipe.commands.RecipeCommand;import recipe.converters.CategoryToCategoryCommand;import recipe.converters.IngredientToIngredientCommand;import recipe.services.CategoryService;import recipe.services.IngredientService;import recipe.services.RecipeService;import static recipe.utility.Mapping.NEW_RECIPE;import static recipe.utility.Mapping.RECIPE;import static recipe.utility.ModelName.M_RECIPE;import static recipe.utility.ViewName.*;/** * This Class Is The Controller Gateway For Recipe Services. */@Slf4j@Controllerpublic class RecipeController {    private RecipeService recipeService;    private IngredientService ingredientService;    private CategoryService categoryService;    private CategoryToCategoryCommand categoryToCategoryCommand;    private IngredientToIngredientCommand ingredientToIngredientCommand;    @Autowired    public RecipeController(RecipeService recipeService, IngredientService ingredientService, CategoryService categoryService, CategoryToCategoryCommand categoryToCategoryCommand, IngredientToIngredientCommand ingredientToIngredientCommand) {        this.recipeService = recipeService;        this.ingredientService = ingredientService;        this.categoryService = categoryService;        this.categoryToCategoryCommand = categoryToCategoryCommand;        this.ingredientToIngredientCommand = ingredientToIngredientCommand;    }    /**     * @param id:   Recipe ID     * @param model :ThemLeaf Appended Model     * @return: The Matched Recipe To The Model.     */    @GetMapping("/recipe/{id}/show")    public String showById(@PathVariable long id, Model model) {        model.addAttribute(M_RECIPE, recipeService.findRecipeById(id));        return SHOW_RECIPE;    }    /**     * @param model: :ThemLeaf Appended Model     * @return: Specified Model To Add New Recipe     */    @GetMapping(NEW_RECIPE)    public String newRecipe(Model model) {        RecipeCommand recipeCommand = new RecipeCommand();        recipeCommand.setCategoryCommand(recipeCommand.getCategoryCommand());        recipeCommand.setCategories(categoryService.getCommandCategories());        recipeCommand.setIngredients(recipeCommand.getIngredients());        recipeCommand.setFlag(true);        model.addAttribute(M_RECIPE, recipeCommand);        return RECIPE_FORM;    }    /**     * @param id:    Recipe ID     * @param model: :ThemLeaf Appended Model     * @return: Specified Model To Update New Recipe     */    @GetMapping("recipe/{id}/update")    public String updateRecipe(@PathVariable String id, Model model) {        RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(id));        recipeCommand.setFlag(false);        model.addAttribute("recipe", recipeCommand);        return RECIPE_FORM;    }    /**     * @param command: RecipeCommand To Save Or Update.     * @return: Saved Or Updated Recipe     */    @PostMapping(RECIPE)    public String saveOrUpdate(@ModelAttribute RecipeCommand command) {        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);        return String.format("%s%x%s", REDIRECT_RECIPE, savedCommand.getId(), V_SHOW);    }    /**     * @param id: Recipe ID To delete.     * @return : redirect to Index Page To Make Sure That The Recipe is deleted.     */    @GetMapping("recipe/{id}/delete")    public String deleteById(@PathVariable String id) {        log.debug("Deleting id: " + id);        recipeService.deleteById(Long.valueOf(id));        return HOME_REDIRECT;    }}