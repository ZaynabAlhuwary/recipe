package recipe.services;

import recipe.commands.CategoryCommand;
import recipe.domain.Category;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CategoryService {

    public Category saveCategory(Category category);
    public Set<Category> getCategories();
    public Set<CategoryCommand> getCommandCategories();
    public Optional<Category> getCategoriesById(Long id);
    public Optional<Category> findByDescription(String desc);

}
