package com.std.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.model.Std;
import com.std.repository.StdRepository;

@Service
public class StdService {

    @Autowired
    private StdRepository repository;

    // Register student
    public void register(Std student) {
        repository.save(student);
    }

    // Login student
    public Std login(String email, String password) {
        return repository.findByEmailAndPassword(email, password);
    }
}
