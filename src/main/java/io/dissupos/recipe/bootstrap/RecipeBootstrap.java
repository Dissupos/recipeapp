package io.dissupos.recipe.bootstrap;

import io.dissupos.recipe.domain.*;
import io.dissupos.recipe.repositories.CategoryRepository;
import io.dissupos.recipe.repositories.RecipeRepository;
import io.dissupos.recipe.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Slf4j
@Component
public class RecipeBootstrap implements ApplicationListener<ApplicationReadyEvent> {
    private RecipeRepository recipeRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public RecipeBootstrap(RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository, CategoryRepository categoryRepository) {
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        log.info("Start event");
        // UOMs
        UnitOfMeasure teaspoonUOM = unitOfMeasureRepository.findByDescription("Teaspoon").get();
        UnitOfMeasure tbspUOM = unitOfMeasureRepository.findByDescription("Tablespoon").get();
        UnitOfMeasure dashUOM = unitOfMeasureRepository.findByDescription("Dash").get();
        UnitOfMeasure eachUOM = unitOfMeasureRepository.findByDescription("Each").get();
        UnitOfMeasure cupUOM = unitOfMeasureRepository.findByDescription("Cup").get();
        UnitOfMeasure pintUOM = unitOfMeasureRepository.findByDescription("Pint").get();

        // Categories
        Category veganCategory = categoryRepository.findByDescription("Vegan").get();
        Category dipCategory = categoryRepository.findByDescription("Dip").get();
        Category mexicanCategory = categoryRepository.findByDescription("Mexican").get();
        Category avocadoCategory = categoryRepository.findByDescription("Avocado").get();
        Category dinnerCategory = categoryRepository.findByDescription("Dinner").get();
        Category grillCategory = categoryRepository.findByDescription("Grill").get();
        Category chickenCategory = categoryRepository.findByDescription("Chicken").get();
        // Guacamole
        Recipe guacRecipe = new Recipe();
        guacRecipe.setDescription("Perfect guacamole!");
        guacRecipe.setPrepTime(10);
        guacRecipe.setServings(4);
        guacRecipe.setCookTime(0);
        guacRecipe.setSource("Simply Recipes");
        guacRecipe.setUrl("http://www.simplyrecipes.com/recipes/perfect_guacamole/");
        guacRecipe.setDifficulty(Difficulty.EASY);
        guacRecipe.setDirections("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\n" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\n" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.");

        // Guacamole Ingredients
        guacRecipe.addIngredient(new Ingredient("ripe avocados", new BigDecimal("2"), eachUOM));
        guacRecipe.addIngredient(new Ingredient("Kosher salt", new BigDecimal(".5"), teaspoonUOM));
        guacRecipe.addIngredient(new Ingredient("fresh lime juice or lemon juice", new BigDecimal("1"), tbspUOM));
        guacRecipe.addIngredient(new Ingredient("minced red onion or thinly sliced green onion", new BigDecimal("2"), tbspUOM));
        guacRecipe.addIngredient(new Ingredient("serrano chiles, stems and seeds removed, minced", new BigDecimal("1"), eachUOM));
        guacRecipe.addIngredient(new Ingredient("cilantro (leaves and tender stems), finely chopped", new BigDecimal("2"), tbspUOM));
        guacRecipe.addIngredient(new Ingredient("freshly grated black pepper", new BigDecimal("2"), dashUOM));
        guacRecipe.addIngredient(new Ingredient("ripe tomato, seeds and pulp removed, chopped", new BigDecimal(".5"), eachUOM));
        // Guacamole categories
        guacRecipe.getCategories().add(veganCategory);
        guacRecipe.getCategories().add(dipCategory);
        guacRecipe.getCategories().add(mexicanCategory);
        guacRecipe.getCategories().add(avocadoCategory);
        // Guacamole Notes
        Notes notes = new Notes();
        notes.setRecipeNotes(
                "For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                        "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries (see our Strawberry Guacamole).\n" +
                        "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\n" +
                        "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.\n" +
                        "For a deviled egg version with guacamole, try our Guacamole Deviled Eggs!");
        guacRecipe.setNotes(notes);
        recipeRepository.save(guacRecipe);

        // Spicy Grilled Chicken Tacos
        Recipe tacoRecipe = new Recipe();
        tacoRecipe.setDescription("Spicy Grilled Chicken Tacos");
        tacoRecipe.setPrepTime(20);
        tacoRecipe.setCookTime(15);
        tacoRecipe.setServings(6);
        tacoRecipe.setSource("Simply Recipes");
        tacoRecipe.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        tacoRecipe.setDifficulty(Difficulty.MODERATE);
        tacoRecipe.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "Spicy Grilled Chicken Tacos\n" +
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.");

        //  Taco ingredients
        tacoRecipe.addIngredient(new Ingredient("ancho chili powder", new BigDecimal("2"), tbspUOM));
        tacoRecipe.addIngredient(new Ingredient("dried oregano", new BigDecimal("1"), teaspoonUOM));
        tacoRecipe.addIngredient(new Ingredient("dried cumin", new BigDecimal("1"), teaspoonUOM));
        tacoRecipe.addIngredient(new Ingredient("sugar", new BigDecimal("1"), teaspoonUOM));
        tacoRecipe.addIngredient(new Ingredient("salt", new BigDecimal(".5"), teaspoonUOM));
        tacoRecipe.addIngredient(new Ingredient("clove garlic, finely chopped", new BigDecimal("1"), eachUOM));
        tacoRecipe.addIngredient(new Ingredient("finely grated orange zest", new BigDecimal("1"), tbspUOM));
        tacoRecipe.addIngredient(new Ingredient("fresh-squeezed orange juice", new BigDecimal("3"), tbspUOM));
        tacoRecipe.addIngredient(new Ingredient("olive oil", new BigDecimal("2"), tbspUOM));
        tacoRecipe.addIngredient(new Ingredient("medium ripe avocados, sliced", new BigDecimal("2"), eachUOM));
        tacoRecipe.addIngredient(new Ingredient("packed baby arugula (3 ounces)", new BigDecimal("3"), cupUOM));
        tacoRecipe.addIngredient(new Ingredient("Roughly chopped cilantro", new BigDecimal("1"), eachUOM));
        tacoRecipe.addIngredient(new Ingredient("skinless, boneless chicken thighs (1 1/4 pounds)", new BigDecimal("6"), eachUOM));
        tacoRecipe.addIngredient(new Ingredient("small corn tortillas", new BigDecimal("8"), eachUOM));
        tacoRecipe.addIngredient(new Ingredient("radishes, thinly sliced", new BigDecimal("4"), eachUOM));
        tacoRecipe.addIngredient(new Ingredient("cherry tomatoes, halved", new BigDecimal(".5"), pintUOM));
        tacoRecipe.addIngredient(new Ingredient("red onion, thinly sliced", new BigDecimal(".25"), eachUOM));
        tacoRecipe.addIngredient(new Ingredient("sour cream thinned with 1/4 cup milk", new BigDecimal(".5"), cupUOM));
        tacoRecipe.addIngredient(new Ingredient("lime, cut into wedges", new BigDecimal("2"), eachUOM));
        // Taco categories
        tacoRecipe.getCategories().add(dinnerCategory);
        tacoRecipe.getCategories().add(grillCategory);
        tacoRecipe.getCategories().add(chickenCategory);
        // Taco notes
        Notes tacoNotes = new Notes();
        tacoNotes.setRecipeNotes("We have a family motto and it is this: Everything goes better in a tortilla.\n" +
                "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.\n" +
                "Today’s tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!\n" +
                "First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\n" +
                "Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes!");
        tacoRecipe.setNotes(tacoNotes);

        recipeRepository.save(tacoRecipe);
    }
}
