package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Chart;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Chart entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ChartRepository extends JpaRepository<Chart, Long> {}
