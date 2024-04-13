package com.meetpe_api.meetpe_api.Repositories;

import com.meetpe_api.meetpe_api.Entities.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponseRepository extends JpaRepository<Response,Long> {
}
