package com.kafein.intern.identity.repository;

import com.kafein.intern.identity.model.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthoritiesRepository extends JpaRepository<Authorities,Long> {

}
