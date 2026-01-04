package com.std.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.std.model.Std;

public interface StdRepository extends JpaRepository<Std, Long> {

    Std findByEmailAndPassword(String email, String password);
}
