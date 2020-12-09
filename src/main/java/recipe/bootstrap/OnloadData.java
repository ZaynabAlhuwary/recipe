/*
 *  Created By  Zaynab Osama ,  On 2/23/19 2:42 PM
 *
*/
package recipe.bootstrap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import recipe.domain.*;
import recipe.repositories.RecipeRepository;
import recipe.services.*;

import java.math.BigDecimal;
import java.util.*;

@Slf4j
@Component
@Profile("default")
public class OnloadData implements ApplicationListener<ContextRefreshedEvent> {

private RecipeRepository recipeRepository;
private NotesService notesService;
private IngredientService ingredientService;
private UnitOfMeasureService unitOfMeasureService;
private CategoryService categoryService;

@Autowired
public OnloadData(RecipeRepository recipeRepository, CategoryService categoryService,  IngredientService ingredientService,NotesService notesService ,UnitOfMeasureService unitOfMeasureService) {
        this.recipeRepository = recipeRepository;
        this.categoryService = categoryService;
        this.notesService = notesService;
        this.ingredientService = ingredientService;
        this.unitOfMeasureService = unitOfMeasureService;
        }

private  List<Recipe> loadData() {

        Recipe perfectGuacamole = new Recipe();
        perfectGuacamole.setDescription("The Best guacamole! So easy to make with ripe avocados!");
        perfectGuacamole.setDifficulty(Difficulty.EASY);
        perfectGuacamole.setPrepTime(15);
        perfectGuacamole.setCookTime(20);
        perfectGuacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        perfectGuacamole.setUrl("https://www.simplyrecipes.com/recipes/chicknWithRice/");
        perfectGuacamole.setDirections("These are Chickn With Rice Direction For Test");

        //seconf recipe
        Recipe chicknWithRice = new Recipe();
        chicknWithRice.setDescription("Chickn With Rice! So easy to make with ripe rice!");
        chicknWithRice.setDifficulty(Difficulty.EASY);
        chicknWithRice.setPrepTime(30);
        chicknWithRice.setCookTime(2);
        chicknWithRice.setUrl("https://www.simplyrecipes.com/recipes/chicknWithRice/");
        chicknWithRice.setDirections("These are Chickn With Rice Direction For Test");

        //Units Of Measure

        Optional<UnitOfMeasure> ounce= unitOfMeasureService.findByUom("Ounce");
        Optional<UnitOfMeasure> TeasPoon= unitOfMeasureService.findByUom("TeasPoon");
        Optional<UnitOfMeasure> TablePoon= unitOfMeasureService.findByUom("TablePoon");
        Optional<UnitOfMeasure> cup= unitOfMeasureService.findByUom("Cup");

        //Categories
        Optional<Category> Egyption =categoryService.findByDescription("Egyptian");
        Optional<Category> Italian =categoryService.findByDescription("Italian");
        Optional<Category> FastFood =categoryService.findByDescription("Fast Food");
        Optional<Category> American=categoryService.findByDescription("American");

        perfectGuacamole.addIngredient(new Ingredient("avocados",new BigDecimal(2),ounce.get()));
        perfectGuacamole.addIngredient(new Ingredient("Kosher Salt",new BigDecimal(1/2),TablePoon.get()));
        perfectGuacamole.getIngredients().add(new Ingredient("lemon juice",new BigDecimal(1),cup.get()));
        perfectGuacamole.addIngredient(new Ingredient("red onion",new BigDecimal(1/4),TeasPoon.get()));

        // second recipe ingredients

        chicknWithRice.addIngredient(new Ingredient("chickn",BigDecimal.valueOf(2),ounce.get()));
        chicknWithRice.addIngredient(new Ingredient("Rice",BigDecimal.valueOf(2),cup.get()));
        chicknWithRice.addIngredient(new Ingredient("oil",BigDecimal.valueOf(1/4),cup.get()));
        chicknWithRice.addIngredient(new Ingredient("red onion",BigDecimal.valueOf(1/4),TeasPoon.get()));

        perfectGuacamole.setCategory(Egyption.get());


        //second Ingredient Catregories

        chicknWithRice.setCategory(Egyption.get());

        Note guacamoleNote = new Note();
        guacamoleNote.setRecipeNotes("The trick to making perfect guacamole is using ripe avocados that are just the right amount of ripeness. " +
        "Not ripe enough and the avocado will be hard and tasteless. " +
        "Too ripe and the taste will be off.");

        guacamoleNote.setRecipe(perfectGuacamole);
        perfectGuacamole.setNote(guacamoleNote);

        // Second Ingredients Notes


        Note chicknWithRiceNotes = new Note();
        guacamoleNote.setRecipeNotes("The trick to making perfect chicknWithRice is using ripe rice " +
        "that are just the right amount of ripeness. Not ripe enough and ");

        chicknWithRiceNotes.setRecipe(chicknWithRice);
        chicknWithRice.setNote(chicknWithRiceNotes);

        List<Recipe> recipes = new ArrayList<Recipe>();
        recipes.add(perfectGuacamole);
        recipes.add(chicknWithRice);

        return recipes;

        }


        @Override
        public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
            List<Recipe> recipes = loadData();
            recipeRepository.saveAll(recipes);
        }
}
