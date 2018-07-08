package io.dissupos.recipe.converters;

import io.dissupos.recipe.commands.IngredientCommand;
import io.dissupos.recipe.domain.Ingredient;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {

    private UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure;

    @Autowired
    public IngredientCommandToIngredient(UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure) {
        this.unitOfMeasureCommandToUnitOfMeasure = unitOfMeasureCommandToUnitOfMeasure;
    }

    @Synchronized
    @Nullable
    @Override
    public Ingredient convert(IngredientCommand source) {
        if (Objects.isNull(source)) {
            return null;
        }

        final Ingredient ingredient = new Ingredient(source.getDescription(),
                source.getAmount(),
                unitOfMeasureCommandToUnitOfMeasure.convert(source.getUnitOfMeasure()));
        ingredient.setId(source.getId());
        return ingredient;
    }
}
