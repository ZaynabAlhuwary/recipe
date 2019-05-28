/*
 * Created By  Zaynab Osama , On:  2/24/19 9:00 PM
 */

package recipe.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import recipe.commands.CategoryCommand;
import recipe.commands.IngredientCommand;
import recipe.commands.RecipeCommand;
import recipe.converters.CategoryToCategoryCommand;
import recipe.converters.IngredientToIngredientCommand;
import recipe.domain.Category;
import recipe.domain.Ingredient;
import recipe.exceptions.NotFoundException;
import recipe.services.CategoryService;
import recipe.services.IngredientService;
import recipe.services.RecipeService;

import java.util.HashSet;
import java.util.Set;

/**
 * This Class Is The Controller Gateway For Recipe Services.
 */
@Slf4j
@Controller
public class RecipeController {

    private RecipeService recipeService;
    private IngredientService ingredientService;
    private CategoryService categoryService;
    private CategoryToCategoryCommand categoryToCategoryCommand;
    private IngredientToIngredientCommand ingredientToIngredientCommand;

    public RecipeController(RecipeService recipeService, IngredientService ingredientService, CategoryService categoryService, CategoryToCategoryCommand categoryToCategoryCommand, IngredientToIngredientCommand ingredientToIngredientCommand) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.categoryService = categoryService;
        this.categoryToCategoryCommand = categoryToCategoryCommand;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
    }

    /**
     * @param id: Recipe ID
     * @param model :ThemLeaf Appended Model
     * @return: The Matched Recipe To The Model.
     */
    @GetMapping("/recipe/{id}/show")
    public String showById(@PathVariable long id, Model model){

        model.addAttribute("recipe", recipeService.findRecipeById(id));

        return "recipe/show";
    }

    /**
     * @param model: :ThemLeaf Appended Model
     * @return: Specified Model To Add New Recipe
     */
    @GetMapping("recipe/new")
    public String newRecipe(Model model){
        RecipeCommand recipeCommand = new RecipeCommand();
       /* if(ingredientService.getIngredient() != null && ingredientService.getIngredient().size() > 0)
        {
            ingredientService.getIngredient()
                    .forEach((Ingredient ingredient) -> recipeCommand.getIngredients()
                            .add(ingredientToIngredientCommand.convert(ingredient)));
        }
       */ recipeCommand.setCategoryCommand(recipeCommand.getCategoryCommand());
        recipeCommand.setCategories(categoryService.getCommandCategories());
        recipeCommand.setIngredients(recipeCommand.getIngredients());
        recipeCommand.setFlag(true);
        model.addAttribute("recipe",recipeCommand );
        return "recipe/recipeform";
    }

    /**
     * @param id: Recipe ID
     * @param model: :ThemLeaf Appended Model
     * @return: Specified Model To Update New Recipe
     */
    @GetMapping("recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model){
        RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(id));
        if (ingredientService.getIngredient() != null && ingredientService.getIngredient().size() > 0){
            ingredientService.getIngredient()
                    .forEach((Ingredient ingredient) -> recipeCommand.getIngredients()
                            .add(ingredientToIngredientCommand.convert(ingredient)));
        }
        recipeCommand.setCategoryCommand(recipeCommand.getCategoryCommand());
        recipeCommand.setCategories(categoryService.getCommandCategories());
        model.addAttribute("recipe",recipeCommand);
        return  "recipe/recipeform";
    }

    /**
     * @param command: RecipeCommand To Save Or Update.
     * @return: Saved Or Updated Recipe
     */
    @PostMapping("recipe")
    public String saveOrUpdate(@ModelAttribute RecipeCommand command){
        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);
        return "redirect:/recipe/" + savedCommand.getId() + "/show";
    }

    /**
     * @param id: Recipe ID To delete.
     * @return : redirect to Index Page To Make Sure That The Recipe is deleted.
     */
    @GetMapping("recipe/{id}/delete")
    public String deleteById(@PathVariable String id){

        log.debug("Deleting id: " + id);
        recipeService.deleteById(Long.valueOf(id));
        return "redirect:/";
    }


}
