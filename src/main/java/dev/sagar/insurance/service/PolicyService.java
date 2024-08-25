package dev.sagar.insurance.service;

import dev.sagar.insurance.dto.PolicyDTO;
import dev.sagar.insurance.entity.Policy;
import dev.sagar.insurance.exception.ResourceNotFoundException;
import dev.sagar.insurance.mapper.PolicyMapper;
import dev.sagar.insurance.repository.PolicyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PolicyService {

    private final PolicyRepository policyRepository;
    private final PolicyMapper policyMapper;

    public PolicyService(PolicyRepository policyRepository, PolicyMapper policyMapper) {
        this.policyRepository = policyRepository;
        this.policyMapper = policyMapper;
    }

    public List<PolicyDTO> getAllPolicies() {
        return policyRepository.findAll().stream().map(policyMapper::toDto).toList();
    }

    public PolicyDTO getPolicyById(Long id) {
        Policy policy = policyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Policy not found with id: " + id));
        return policyMapper.toDto(policy);
    }


    public PolicyDTO createPolicy(PolicyDTO policyDTO) {
        Policy policy = policyMapper.toEntity(policyDTO);
        return policyMapper.toDto(policyRepository.save(policy));
    }

    public PolicyDTO updatePolicy(Long id, PolicyDTO policyDTO) {
        Policy existingPolicy = policyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Policy not found with id: " + id));

        existingPolicy.setPolicyNumber(policyDTO.policyNumber());
        existingPolicy.setType(policyDTO.type());
        existingPolicy.setCoverageAmount(policyDTO.coverageAmount());
        existingPolicy.setPremium(policyDTO.premium());
        existingPolicy.setStartDate(policyDTO.startDate());
        existingPolicy.setEndDate(policyDTO.endDate());

        return policyMapper.toDto(policyRepository.save(existingPolicy));
    }

    public void deletePolicy(Long id) {
        Policy policy = policyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Policy not found with id: " + id));
        policyRepository.delete(policy);
    }
}