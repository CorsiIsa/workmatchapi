package br.com.fiap.workmatch.opportunity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OpportunityRepository extends JpaRepository<Opportunity, Long>{
    List<Opportunity> findByTitleContainingIgnoreCase(String title);
} 
