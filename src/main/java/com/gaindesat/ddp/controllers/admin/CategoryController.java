package com.gaindesat.ddp.controllers.admin;

import com.gaindesat.ddp.dto.CategoryDTO;
import com.gaindesat.ddp.models.Category;
import com.gaindesat.ddp.repository.CategoryRepository;
import com.gaindesat.ddp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/admin")
@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/categories")
    @Produces({MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Iterable<CategoryDTO>> getAllCategories() {

        Iterable<Category> allCategories = this.categoryRepository.findAll();
        List<CategoryDTO> categoryDTOList = new ArrayList<>();
        allCategories.forEach(category -> {
            CategoryDTO categoryDTO = new CategoryDTO(
                    category.getUuid(),
                    category.getCode(),
                    category.getCatName(),
                    category.getUsers().size()

            );
            categoryDTOList.add(categoryDTO);
        });

        return new ResponseEntity<>(categoryDTOList, HttpStatus.OK);
    }

    @GetMapping(value = "/categories/{catId}")
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getCategory(@PathVariable UUID catId) {
        Optional<Category> optionalCategory = this.categoryRepository.findById(catId);

      return optionalCategory.<ResponseEntity<Object>>map( category ->
              new ResponseEntity<>(category, HttpStatus.OK)).orElseGet(() ->
              new ResponseEntity<>("{Category not found}", HttpStatus.NOT_FOUND));
    }

    @PostMapping("/categories")
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createCategory(@RequestBody CategoryDTO categoryDTO) {
        Category persistenceCategory = categoryService.populateCategory(categoryDTO, new Category());
        try {
            categoryRepository.save(persistenceCategory);

            HttpHeaders responseHeaders = new HttpHeaders();
            URI newCatUri = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("{id}")
                    .buildAndExpand(persistenceCategory.getUuid())
                    .toUri();
            responseHeaders.setLocation(newCatUri);
            return new ResponseEntity<>(persistenceCategory, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occur, Request Aborted", HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/categories/{catId}")
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateCategory(@RequestBody CategoryDTO categoryDTO, @PathVariable UUID catId) {
        Optional<Category> categoryOptional = categoryRepository.findById(catId);

        if(categoryOptional.isPresent()) {
            Category persistenceCategory = categoryService.populateCategory(categoryDTO, categoryOptional.get());
            categoryRepository.save(persistenceCategory);

            return new ResponseEntity<>(persistenceCategory, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Not found !", HttpStatus.NOT_MODIFIED);
        }
    }

    @DeleteMapping("/categories/{catId}")
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteCategory(@PathVariable UUID catId) {
        categoryRepository.deleteById(catId);
        return new ResponseEntity<>("category removed successfully", HttpStatus.OK);
    }
}
