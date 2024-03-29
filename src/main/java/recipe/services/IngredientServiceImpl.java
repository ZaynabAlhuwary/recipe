/*
 *  Created By  Zaynab Osama ,  On 2/23/19 2:45 PM
 *
 */

package recipe.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import recipe.commands.IngredientCommand;
import recipe.converters.IngredientCommandToIngredient;
import recipe.converters.IngredientToIngredientCommand;
import recipe.domain.Ingredient;
import recipe.domain.Recipe;
import recipe.exceptions.NotFoundException;
import recipe.repositories.IngredientRepository;
import recipe.repositories.RecipeRepository;
import recipe.repositories.UnitOfMeasureRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    private IngredientRepository ingredientRepository;
    private RecipeRepository recipeRepository;
    private IngredientToIngredientCommand ingredientToIngredientCommand;
    private IngredientCommandToIngredient ingredientCommandToIngredient;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository, RecipeRepository recipeRepository, IngredientToIngredientCommand ingredientToIngredientCommand, IngredientCommandToIngredient ingredientCommandToIngredient, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.ingredientRepository = ingredientRepository;
        this.recipeRepository = recipeRepository;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void saveIngredient(Ingredient ingredient) {
        this.ingredientRepository.save(ingredient);
    }

    @Override
    public Optional<Ingredient> getIngredientById(Long id) {
        Optional<Ingredient> ingredient = ingredientRepository.findById(id);
        return ingredient;
    }

    @Override
    public Set<Ingredient> getIngredient() {
        Set<Ingredient> ingredients = new HashSet<Ingredient>();
        ingredientRepository.findAll().iterator().forEachRemaining(ingredients::add);
        return ingredients;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {

        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if (!recipeOptional.isPresent()) {
            final String errorMsg = String.format("%s%s", "recipe id not found. Id: ", recipeId.toString());
            log.error(errorMsg);
            throw new NotFoundException(errorMsg);
        }

        Recipe recipe = recipeOptional.get();

        Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map(ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst();

        if (!ingredientCommandOptional.isPresent()) {
                final String errorMsg = String.format("%s%s","Ingredient id not found: " ,ingredientId.toString());
                log.error(errorMsg);
                throw new NotFoundException(errorMsg);
        }

        IngredientCommand ingredientCommand = ingredientCommandOptional.get();
        ingredientCommand.setRecipeId(recipeId);
        return ingredientCommand;
    }


    @Override
    @Transactional
    public IngredientCommand saveIngredientCommand(IngredientCommand command) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(command.getRecipeId());

        if (!recipeOptional.isPresent()) {
            log.error("Recipe not found for id: " + command.getRecipeId());
            return new IngredientCommand();
        } else {
            Recipe recipe = recipeOptional.get();

            Optional<Ingredient> ingredientOptional = recipe
                    .getIngredients()
                    .stream()
                    .filter(ingredient -> ingredient.getId().equals(command.getId()))
                    .findFirst();

            if (ingredientOptional.isPresent()) {
                // Update The Ingredient Data.
                Ingredient ingredientFound = ingredientOptional.get();
                ingredientFound.setDescription(command.getDescription());
                ingredientFound.setAmount(command.getAmount());
            } else {
                //Add new Ingredient
                Ingredient ingredient = ingredientCommandToIngredient.convert(command);
                ingredient.setRecipe(recipe);
                recipe.addIngredient(ingredient);
            }

            Recipe savedRecipe = recipeRepository.save(recipe);

            Optional<Ingredient> savedIngredientOptional = savedRecipe.getIngredients().stream()
                    .filter(recipeIngredients -> recipeIngredients.getDescription().equals(command.getDescription()))
                    .findFirst();

            if (!savedIngredientOptional.isPresent()) {
                savedIngredientOptional = savedRecipe.getIngredients().stream()
                        .filter(recipeIngredients -> recipeIngredients.getDescription().equals(command.getDescription()))
                        .filter(recipeIngredients -> recipeIngredients.getAmount().equals(command.getAmount()))
                        .filter(recipeIngredients -> recipeIngredients.getUnitOfMeasure().getId().equals(command.getUnitOfMeasureId()))
                        .findFirst();

            }


            IngredientCommand ingredientCommand = ingredientToIngredientCommand.convert(savedIngredientOptional.get());
            ingredientCommand.setRecipeId(savedRecipe.getId());
            return ingredientCommand;
        }

    }

    @Override
    public void deleteById(Long recipeId, Long idToDelete) {

        log.debug("Deleting ingredient: " + recipeId + ":" + idToDelete);

        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if (!recipeOptional.isPresent()) {
            final String errorMsg = String.format("%s%s","Recipe id not found: " ,recipeId.toString());
            log.error(errorMsg);
            throw new NotFoundException(errorMsg);
        }
        Recipe recipe = recipeOptional.get();

        Optional<Ingredient> ingredientOptional = recipe
                .getIngredients()
                .stream()
                .filter(ingredient -> ingredient.getId().equals(idToDelete))
                .findFirst();

        if (!ingredientOptional.isPresent()) {
            final String errorMsg = String.format("%s%s","Ingredient Not Found With This Id " ,idToDelete.toString());
            log.error(errorMsg);
            throw new NotFoundException(errorMsg);
        }

        Ingredient ingredientToDelete = ingredientOptional.get();
        ingredientToDelete.setRecipe(null);
        recipe.getIngredients().remove(ingredientOptional.get());
        recipeRepository.save(recipe);

    }
}
