/*
 *  Created By  Zaynab Osama ,  On 2/23/19 2:45 PM
 *
 */

package recipe.services;

import org.springframework.stereotype.Service;
import recipe.commands.CategoryCommand;
import recipe.converters.CategoryToCategoryCommand;
import recipe.domain.Category;
import recipe.repositories.CategoryRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    private CategoryToCategoryCommand categoryToCategoryCommand;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryToCategoryCommand categoryToCategoryCommand) {
        this.categoryRepository = categoryRepository;
        this.categoryToCategoryCommand = categoryToCategoryCommand;
    }

    @Override
    public Category saveCategory(Category category){
        return categoryRepository.save(category);

    }

    @Override
    public Set<Category> getCategories(){

        Set<Category> categories = new HashSet<Category>();
        categoryRepository.findAll().iterator().forEachRemaining(categories::add);
        return categories;
    }

    @Override
    public  Optional<Category> getCategorieById(Long id){

        return Optional.ofNullable(categoryRepository.findById(id).orElseThrow(() -> new NullPointerException("Category Not Found With Id " + id)));
    }

    @Override
    public Optional<Category> findByDescription(String desc) {
        return categoryRepository.findByDescription(desc);
    }

    @Override
    public Set<CategoryCommand> getCommandCategories() {
        return StreamSupport.stream(categoryRepository.findAll()
                .spliterator(), false)
                .map(categoryToCategoryCommand::convert)
                .collect(Collectors.toSet());
    }
}
