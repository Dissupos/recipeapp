package io.dissupos.recipe.converters;

import io.dissupos.recipe.commands.CategoryCommand;
import io.dissupos.recipe.domain.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand> {

    @Synchronized
    @Nullable
    @Override
    public CategoryCommand convert(Category source) {
        if (Objects.isNull(source)) {
            return null;
        }

        final CategoryCommand category = new CategoryCommand();
        category.setId(source.getId());
        category.setDescription(source.getDescription());
        return category;
    }
}
