package io.dissupos.recipe.controllers;

import io.dissupos.recipe.commands.RecipeCommand;
import io.dissupos.recipe.converters.RecipeToRecipeCommand;
import io.dissupos.recipe.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RecipeController {
    private RecipeService recipeService;

    @Autowired
    private RecipeToRecipeCommand recipeToRecipeCommand;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }


    @GetMapping("recipe/{id}/show")
    public String getRecipe(@PathVariable Long id, Model model) {
        model.addAttribute("recipe", recipeService.findById(id));
        return "recipe/show";
    }

    @GetMapping("recipe/new")
    public String newRecipe(Model model) {
        model.addAttribute("recipe", new RecipeCommand());
        return "recipe/recipeform";
    }

    @GetMapping("recipe/{id}/update")
    public String editRecipe(@PathVariable Long id, Model model) {
        model.addAttribute("recipe", recipeService.findCommandById(id));
        return "recipe/recipeform";
    }

    @PostMapping("recipe")
    public String saveOrUpdate(@ModelAttribute RecipeCommand recipeCommand) {
        RecipeCommand ret = recipeService.saveRecipeCommand(recipeCommand);
        return "redirect:/recipe/" + ret.getId() + "/show/";
    }

    @GetMapping("/recipe/{id}/delete")
    public String deleteRecipe(@PathVariable Long id) {
        recipeService.deleteById(id);
        return "redirect:/";
    }
}
