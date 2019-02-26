/*
 *  Created By  Zaynab Osama ,  On 2/23/19 2:43 PM
 *
 */

package recipe.controllers;



import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import recipe.domain.Recipe;
import recipe.services.RecipeService;

import java.util.Set;

@Slf4j
@Controller
public class IndexController {

    private RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }


    @RequestMapping({"","/","/index"})
    public String getRecipes(Model model){
        log.debug("Inside Get Recipes");
        Set<Recipe> recipes = recipeService.getRecipes();
        model.addAttribute("recipes",recipes);
        return "index";
    }


}
