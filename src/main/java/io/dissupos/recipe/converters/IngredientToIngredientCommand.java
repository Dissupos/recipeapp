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
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {

    private UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

    @Autowired
    public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand) {
        this.unitOfMeasureToUnitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand;
    }

    @Synchronized
    @Nullable
    @Override
    public IngredientCommand convert(Ingredient source) {
        if (Objects.isNull(source)) {
            return null;
        }

        final IngredientCommand ingredient = new IngredientCommand(source.getId(), source.getDescription(),
                source.getAmount(),
                unitOfMeasureToUnitOfMeasureCommand.convert(source.getUnitOfMeasure()));
        return ingredient;
    }
}
