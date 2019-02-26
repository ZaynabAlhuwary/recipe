package recipe.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import recipe.converters.CategoryToCategoryCommand;
import recipe.domain.Category;
import recipe.repositories.CategoryRepository;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CategoryServiceImplTest {

    private CategoryService categoryService;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    CategoryToCategoryCommand categoryToCategoryCommand;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        categoryService = new CategoryServiceImpl(categoryRepository,categoryToCategoryCommand);
    }

    @Test
    public void saveCategory() throws Exception {

        Category category = new Category();
        category.setId(1L);
        category.setDescription("desc");
        when(categoryService.saveCategory(category)).thenReturn(category);
        Category category1 = categoryService.saveCategory(category);
        assertEquals(category,category1);
    }

    @Test
    public void getCategories() throws Exception {

        Set<Category> categories = new HashSet<>();

        categories.add(new Category());
        when(categoryService.getCategories()).thenReturn(categories);

        Set<Category> categoriesReturned =  categoryService.getCategories();

        assertNotEquals("return objects found",categoriesReturned);
        verify(categoryRepository,times(1)).findAll();
        assertEquals(1,categoriesReturned.size());
    }

    @Test
    public void getCategorieById() throws Exception {
        Long idValue = 1L;
        Category category = new Category();
        category.setId(idValue);
        category.setDescription("category");
        Optional<Category> categoryOptional = Optional.of(category);

        when(categoryService.getCategorieById(anyLong())).thenReturn(categoryOptional);

        Optional<Category> categoryOptional1 = categoryService.getCategorieById(idValue);
        assertEquals(idValue,categoryOptional1.get().getId());
    }

    @Test
    public void findByDescription() throws Exception {

        String desc = "category desc.....";
        Category category = new Category();
        category.setId(1L);
        category.setDescription(desc);
        when(categoryService.findByDescription(anyString())).thenReturn(Optional.of(category));

        Optional<Category> categoryOptional = categoryService.findByDescription(desc);

        assertEquals(desc,categoryOptional.get().getDescription());
        verify(categoryRepository,times(1)).findByDescription(anyString());
    }

}