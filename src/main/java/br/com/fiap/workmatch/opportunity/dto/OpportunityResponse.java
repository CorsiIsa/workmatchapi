package br.com.fiap.workmatch.opportunity.dto;

import java.time.LocalDateTime;

import br.com.fiap.workmatch.opportunity.Opportunity;
import br.com.fiap.workmatch.users.User;

public record OpportunityResponse (
    String title,
    String description,
    String responsibilities,
    String benefits,
    String location,
    String contractType,
    String salary,
    String companyName,
    String industrySector,
    LocalDateTime createdAt
){
    public Opportunity toModel(User user){
        return Opportunity
            .builder()
            .title(title)
            .description(description)
            .responsibilities(responsibilities)
            .benefits(benefits)
            .location(location)
            .contractType(contractType)
            .salary(salary)
            .companyName(companyName)
            .industrySector(industrySector)
            .createdAt(LocalDateTime.now())
            .user(user)
            .build();
    }
}
