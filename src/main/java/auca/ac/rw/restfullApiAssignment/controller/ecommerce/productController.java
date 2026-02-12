package auca.ac.rw.restfullApiAssignment.controller.ecommerce;

import auca.ac.rw.restfullApiAssignment.modal.ecommerce.product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class productController {

    private List<product> products = new ArrayList<>();
    private Long nextId = 1L;

    public productController() {
        products.add(new product(nextId++, "iPhone 14 Pro", "Latest Apple smartphone", 999.99, "Electronics", 50, "Apple"));
        products.add(new product(nextId++, "Samsung Galaxy S23", "Premium Android phone", 899.99, "Electronics", 30, "Samsung"));
        products.add(new product(nextId++, "MacBook Pro", "16-inch laptop", 2499.99, "Electronics", 15, "Apple"));
        products.add(new product(nextId++, "Nike Air Max", "Running shoes", 129.99, "Footwear", 100, "Nike"));
        products.add(new product(nextId++, "Adidas Ultraboost", "Comfortable sneakers", 159.99, "Footwear", 75, "Adidas"));
        products.add(new product(nextId++, "Sony WH-1000XM5", "Noise-canceling headphones", 399.99, "Electronics", 40, "Sony"));
        products.add(new product(nextId++, "Levi's 501 Jeans", "Classic denim jeans", 69.99, "Clothing", 200, "Levi's"));
        products.add(new product(nextId++, "North Face Jacket", "Waterproof outdoor jacket", 249.99, "Clothing", 0, "North Face"));
        products.add(new product(nextId++, "Canon EOS R5", "Professional camera", 3899.99, "Electronics", 5, "Canon"));
        products.add(new product(nextId++, "Kindle Paperwhite", "E-reader device", 139.99, "Electronics", 60, "Amazon"));
    }

    @GetMapping
    public ResponseEntity<List<product>> getAllProducts(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer limit) {
        if (page != null && limit != null) {
            int start = page * limit;
            int end = Math.min(start + limit, products.size());
            
            if (start >= products.size()) {
                return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
            }
            
            List<product> paginatedProducts = products.subList(start, end);
            return new ResponseEntity<>(paginatedProducts, HttpStatus.OK);
        }
        
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<product> getProductById(@PathVariable Long productId) {
        Optional<product> product = products.stream()
                .filter(p -> p.getProductId().equals(productId))
                .findFirst();
        
        return product.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<product>> getProductsByCategory(@PathVariable String category) {
        List<product> result = products.stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
        
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/brand/{brand}")
    public ResponseEntity<List<product>> getProductsByBrand(@PathVariable String brand) {
        List<product> result = products.stream()
                .filter(p -> p.getBrand().equalsIgnoreCase(brand))
                .collect(Collectors.toList());
        
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<product>> searchProducts(@RequestParam String keyword) {
        List<product> result = products.stream()
                .filter(p -> p.getName().toLowerCase().contains(keyword.toLowerCase()) ||
                           p.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
        
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/price-range")
    public ResponseEntity<List<product>> getProductsByPriceRange(@RequestParam Double min, @RequestParam Double max) {
        List<product> result = products.stream()
                .filter(p -> p.getPrice() >= min && p.getPrice() <= max)
                .collect(Collectors.toList());
        
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/in-stock")
    public ResponseEntity<List<product>> getProductsInStock() {
        List<product> result = products.stream()
                .filter(p -> p.getStockQuantity() > 0)
                .collect(Collectors.toList());
        
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<product> addProduct(@RequestBody product product) {
        product.setProductId(nextId++);
        products.add(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<product> updateProduct(@PathVariable Long productId, @RequestBody product updatedProduct) {
        Optional<product> existingProduct = products.stream()
                .filter(p -> p.getProductId().equals(productId))
                .findFirst();
        
        if (existingProduct.isPresent()) {
            product product = existingProduct.get();
            product.setName(updatedProduct.getName());
            product.setDescription(updatedProduct.getDescription());
            product.setPrice(updatedProduct.getPrice());
            product.setCategory(updatedProduct.getCategory());
            product.setStockQuantity(updatedProduct.getStockQuantity());
            product.setBrand(updatedProduct.getBrand());
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{productId}/stock")
    public ResponseEntity<product> updateStock(@PathVariable Long productId, @RequestParam int quantity) {
        Optional<product> existingProduct = products.stream()
                .filter(p -> p.getProductId().equals(productId))
                .findFirst();
        
        if (existingProduct.isPresent()) {
            product product = existingProduct.get();
            product.setStockQuantity(quantity);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        boolean removed = products.removeIf(p -> p.getProductId().equals(productId));
        
        if (removed) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

