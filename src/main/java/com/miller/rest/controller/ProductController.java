package com.miller.rest.controller;

import com.miller.domain.entity.Product;
import com.miller.domain.repository.ProductRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import static org.springframework.http.HttpStatus.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductRepository repository;

    public ProductController(ProductRepository repository) { this.repository = repository; }

    @GetMapping(value = "{id}")
    public Product getProductById(@PathVariable Integer id) {
        return  repository.findById(id).
                orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Product not found"));
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Product saveProduct(@RequestBody @Valid Product product) {
        return repository.save(product);
    }

    @DeleteMapping(value = "{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteProduct(@PathVariable Integer id) {
        repository.findById(id)
                .map(product -> {
                    repository.delete(product);
                    return Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Product not found"));
    }

    @PutMapping(value = "{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateProduct(@PathVariable Integer id, @RequestBody @Valid Product product) {
        repository.findById(id)
                .map(existsProduct -> {
                    product.setId(existsProduct.getId());
                    repository.save(product);
                    return Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Product not found"));
    }

    @GetMapping
    public List<Product> searchProducts(Product filter) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withIgnorePaths("id")
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<Product> example = Example.of(filter, matcher);

        return repository.findAll(example);
    }
}
