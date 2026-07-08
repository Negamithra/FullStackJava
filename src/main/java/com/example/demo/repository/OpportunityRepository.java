package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Opportunity;
import com.example.demo.entity.Opportunity.OpportunityStatus;

@Repository
public interface OpportunityRepository extends JpaRepository<Opportunity, Long> {

    Optional<Opportunity> findByTitle(String title);

    boolean existsByTitle(String title);

    List<Opportunity> findByStatus(OpportunityStatus status);

    List<Opportunity> findByLocation(String location);

}