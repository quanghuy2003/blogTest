package com.example.blogtest.conttroller;


import com.example.blogtest.model.Category;
import com.example.blogtest.model.Home;
import com.example.blogtest.service.CategoryService;
import com.example.blogtest.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/homes")
public class HomeController {
    @Autowired
    HomeService homeService;

    @GetMapping
    public ResponseEntity<Iterable<Home>> findAllMode() {
        List<Home> homes = (List<Home>) homeService.findAll();
        if (homes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(homes, HttpStatus.OK);
    }
    @PostMapping("/save")
    public ResponseEntity<Home> save(@RequestBody Home home) {
        homeService.save(home);
        return new ResponseEntity<>( HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Home> findById(@PathVariable Long id) {
        return new ResponseEntity<>(homeService.findById(id).get(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Home> update(@PathVariable Long id, @RequestBody Home home) {
        Optional<Home> homeOptional = homeService.findById(id);
        home.setId(homeOptional.get().getId());
        homeService.save(home);
        return new ResponseEntity<>(homeOptional.get(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Home> delete(@PathVariable Long id) {
        Optional<Home> home = homeService.findById(id);
        if (!home.isPresent()) {
            return new ResponseEntity<>(home.get(), HttpStatus.NOT_FOUND);
        }
        homeService.remove(id);
        return new ResponseEntity<>(home.get(), HttpStatus.NO_CONTENT);
    }

    @Autowired
    CategoryService categoryService;

    @GetMapping("/category")
    public ResponseEntity<Iterable<Category>> findByMode() {
        List<Category> categories = (List<Category>) categoryService.findAll();
        if (categories.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
}
