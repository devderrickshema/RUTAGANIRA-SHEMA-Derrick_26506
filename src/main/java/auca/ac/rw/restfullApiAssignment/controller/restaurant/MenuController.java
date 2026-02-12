package auca.ac.rw.restfullApiAssignment.controller.restaurant;

import auca.ac.rw.restfullApiAssignment.modal.restaurant.MenuItem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

    private List<MenuItem> menuItems = new ArrayList<>();
    private Long nextId = 1L;

    public MenuController() {
        menuItems.add(new MenuItem(nextId++, "Spring Rolls", "Crispy vegetable spring rolls", 8.99, "Appetizer", true));
        menuItems.add(new MenuItem(nextId++, "Caesar Salad", "Fresh romaine with parmesan", 12.99, "Appetizer", true));
        menuItems.add(new MenuItem(nextId++, "Grilled Salmon", "Atlantic salmon with vegetables", 24.99, "Main Course", true));
        menuItems.add(new MenuItem(nextId++, "Pasta Carbonara", "Classic Italian pasta", 18.99, "Main Course", true));
        menuItems.add(new MenuItem(nextId++, "Ribeye Steak", "12oz premium cut", 32.99, "Main Course", false));
        menuItems.add(new MenuItem(nextId++, "Chocolate Lava Cake", "Warm chocolate cake", 9.99, "Dessert", true));
        menuItems.add(new MenuItem(nextId++, "Tiramisu", "Italian coffee dessert", 8.99, "Dessert", true));
        menuItems.add(new MenuItem(nextId++, "Fresh Juice", "Orange or Apple", 4.99, "Beverage", true));
    }

    @GetMapping
    public ResponseEntity<List<MenuItem>> getAllMenuItems() {
        return new ResponseEntity<>(menuItems, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuItem> getMenuItemById(@PathVariable Long id) {
        Optional<MenuItem> item = menuItems.stream()
                .filter(m -> m.getId().equals(id))
                .findFirst();
        
        return item.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<MenuItem>> getMenuItemsByCategory(@PathVariable String category) {
        List<MenuItem> result = menuItems.stream()
                .filter(m -> m.getCategory().equalsIgnoreCase(category))
                .toList();
        
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/available")
    public ResponseEntity<List<MenuItem>> getAvailableItems(@RequestParam boolean available) {
        List<MenuItem> result = menuItems.stream()
                .filter(m -> m.isAvailable() == available)
                .toList();
        
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<MenuItem>> searchMenuItems(@RequestParam String name) {
        List<MenuItem> result = menuItems.stream()
                .filter(m -> m.getName().toLowerCase().contains(name.toLowerCase()))
                .toList();
        
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MenuItem> addMenuItem(@RequestBody MenuItem item) {
        item.setId(nextId++);
        menuItems.add(item);
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/availability")
    public ResponseEntity<MenuItem> toggleAvailability(@PathVariable Long id) {
        Optional<MenuItem> item = menuItems.stream()
                .filter(m -> m.getId().equals(id))
                .findFirst();
        
        if (item.isPresent()) {
            MenuItem menuItem = item.get();
            menuItem.setAvailable(!menuItem.isAvailable());
            return new ResponseEntity<>(menuItem, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable Long id) {
        boolean removed = menuItems.removeIf(m -> m.getId().equals(id));
        
        if (removed) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

