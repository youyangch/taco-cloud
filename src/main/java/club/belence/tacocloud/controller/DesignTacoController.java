package club.belence.tacocloud.controller;

import club.belence.tacocloud.dao.IngredientRepository;
import club.belence.tacocloud.dao.impl.IngredientRepositoryImpl;
import club.belence.tacocloud.entity.Ingredient;
import club.belence.tacocloud.entity.Ingredient.Type;
import club.belence.tacocloud.entity.Taco;
import club.belence.tacocloud.entity.TacoOrder;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {

    private final IngredientRepository ingredientRepository;

    @Autowired
    DesignTacoController(IngredientRepository ingredientRepository){
        this.ingredientRepository = ingredientRepository;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model){

        Iterable<Ingredient> ingredients = ingredientRepository.findAll();

        Type[] types = Ingredient.Type.values();

        for(Type type:types){
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }

    }

    @ModelAttribute(name="tacoOrder")
    public TacoOrder order(){
        return new TacoOrder();
    }

    @ModelAttribute(name="taco")
    public Taco taco(){
        return new Taco();
    }

    @GetMapping
    public String showDesignForm(Model model){
        return "design";
    }

    @PostMapping
    public String processTaco(@Valid Taco taco, Errors errors , @ModelAttribute TacoOrder tacoOrder){

        if(errors.hasErrors()){
            return "design";
        }

        tacoOrder.addTaco(taco);
        log.info("Processing taco: {}",taco);

        return "redirect:/orders/current";
    }

    private Iterable<Ingredient> filterByType(Iterable<Ingredient> ingredients, Type type){
        return StreamSupport.stream(ingredients.spliterator(),false)
                .filter(i -> i.getType().equals(type))
                .collect(Collectors.toList());
    }

}
