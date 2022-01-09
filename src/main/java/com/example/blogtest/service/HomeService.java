package com.example.blogtest.service;

import com.example.blogtest.model.Home;
import com.example.blogtest.repository.HomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class HomeService implements IHomeService {

    @Autowired
    private HomeRepository homeRepository;

    @Override
    public void save(Home home) {
        homeRepository.save(home);
    }

    @Override
    public Iterable<Home> findAll() {
        return homeRepository.findAll();
    }

    @Override
    public Optional<Home> findById(Long id) {
        return homeRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        homeRepository.deleteById(id);
    }
}
