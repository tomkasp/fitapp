package com.tomkasp.fitapp_common.repository;

import com.tomkasp.fitapp_common.domain.Diet;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Diet entity.
 */
@SuppressWarnings("unused")
public interface DietRepository extends JpaRepository<Diet,Long> {

}
