package dev.java10x.MagicFridgeAI.controller;

import dev.java10x.MagicFridgeAI.model.FoodItem;
import dev.java10x.MagicFridgeAI.service.FoodItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/food")
public class FoodItemController {

    private FoodItemService foodItemService;

    public FoodItemController(FoodItemService foodItemService) {
        this.foodItemService = foodItemService;
    }

    // POST
    @PostMapping("/create")
    public ResponseEntity<FoodItem> createFood(@RequestBody FoodItem foodItem) {
        /*No padrão Rest quando criamos algo devemos dizer ao cliente, onde ele pode encontrar esse novo recurso(URL dele)*/
        FoodItem foodItemSaved = foodItemService.save(foodItem);

        // Criando a URI do novo recurso (obs veja que passamos o id do novo recurso)
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(foodItemSaved.getId())
                .toUri();

        // Retornamos o status 201, o Header: location e objeto do item criado no corpo
        return ResponseEntity.created(uri).body(foodItemSaved);
    }

    // GET
    @GetMapping("/list")
    public ResponseEntity<List<FoodItem>> listAllFood() {
        List<FoodItem> foodItems = foodItemService.listAll();
        return ResponseEntity.ok(foodItems);
    }

    @GetMapping("list/{id}")
    public ResponseEntity<?> listFoodById(@PathVariable Long id) {
        FoodItem foodItemFound = foodItemService.listById(id);

        if (foodItemFound == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no food with the ID: " + id);
        }

        return ResponseEntity.ok(foodItemFound);
    }

    // UPDATE
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateFoodItem(@PathVariable Long id, @RequestBody FoodItem foodItemBody) {
        FoodItem foodItemUpdated = foodItemService.update(id, foodItemBody);

        if (foodItemUpdated == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no food with the ID: " + id);
        }

        return ResponseEntity.ok(foodItemUpdated);
    }

    // DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteFoodItem(@PathVariable Long id) {
        if (foodItemService.listById(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no food with the ID: " + id);
        }

        foodItemService.deleteById(id);
        return ResponseEntity.ok("deleted food with the ID: " + id);
    }

}
