package io.dissupos.recipe;

import io.dissupos.recipe.domain.UnitOfMeasure;
import io.dissupos.recipe.repositories.UnitOfMeasureRepository;
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
public class UnitOfMeasureRepositoryIT {

    @Autowired
    private UnitOfMeasureRepository unitOfMeasureRepository;

    @Test
    public void findByDescription() {
        String pintName = "Pint";
        String cupName = "Cup";
        Optional<UnitOfMeasure> pintOptional = unitOfMeasureRepository.findByDescription(pintName);
        Optional<UnitOfMeasure> cupOptional = unitOfMeasureRepository.findByDescription(cupName);

        assertTrue(pintOptional.isPresent());
        assertTrue(cupOptional.isPresent());

        assertEquals(pintName, pintOptional.get().getDescription());
        assertEquals(cupName, cupOptional.get().getDescription());
    }
}
