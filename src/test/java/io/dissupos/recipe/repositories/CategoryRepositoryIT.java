package io.dissupos.recipe.repositories;

import io.dissupos.recipe.domain.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CategoryRepositoryIT {
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void testFindByDescription() {
        String mexican = "Mexican";
        String vegan = "Vegan";

        Optional<Category> mexicanOptional = categoryRepository.findByDescription(mexican);
        Optional<Category> veganOptional = categoryRepository.findByDescription(vegan);

        assertTrue(mexicanOptional.isPresent());
        assertTrue(veganOptional.isPresent());

        assertEquals(mexican, mexicanOptional.get().getDescription());
        assertEquals(vegan, veganOptional.get().getDescription());

    }
}
