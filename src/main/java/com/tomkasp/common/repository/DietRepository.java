package com.tomkasp.common.repository;

import com.tomkasp.common.domain.Diet;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Diet entity.
 */
@SuppressWarnings("unused")
public interface DietRepository extends JpaRepository<Diet,Long> {

}
