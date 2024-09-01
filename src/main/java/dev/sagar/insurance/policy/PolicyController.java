package dev.sagar.insurance.policy;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * This class is responsible for handling HTTP requests related to policies.
 * It provides endpoints for retrieving, creating, updating, and deleting policies.
 *
 * @author nathsagar96
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/policies")
public class PolicyController {

    private final PolicyService policyService;

    /**
     * Retrieves all policies.
     *
     * @return A ResponseEntity containing a list of PolicyDTO objects and a status code of OK (200).
     */
    @GetMapping
    public ResponseEntity<List<PolicyDTO>> getAllPolicies() {
        return new ResponseEntity<>(policyService.getAllPolicies(), HttpStatus.OK);
    }

    /**
     * Retrieves a policy by its ID.
     *
     * @param id The ID of the policy to retrieve.
     * @return A ResponseEntity containing the requested PolicyDTO object and a status code of OK (200).
     */
    @GetMapping("/{id}")
    public ResponseEntity<PolicyDTO> getPolicyById(@PathVariable Long id) {
        return new ResponseEntity<>(policyService.getPolicyById(id), HttpStatus.OK);
    }

    /**
     * Creates a new policy.
     *
     * @param policyDTO The PolicyDTO object containing the details of the new policy.
     * @return A ResponseEntity containing the created PolicyDTO object and a status code of CREATED (201).
     */
    @PostMapping
    public ResponseEntity<PolicyDTO> createPolicy(@Valid @RequestBody PolicyDTO policyDTO) {
        return new ResponseEntity<>(policyService.createPolicy(policyDTO), HttpStatus.CREATED);
    }

    /**
     * Updates an existing policy.
     *
     * @param id        The ID of the policy to update.
     * @param policyDTO The updated PolicyDTO object containing the new details.
     * @return A ResponseEntity containing the updated PolicyDTO object and a status code of OK (200).
     */
    @PutMapping("/{id}")
    public ResponseEntity<PolicyDTO> updatePolicy(@PathVariable Long id, @Valid @RequestBody PolicyDTO policyDTO) {
        return new ResponseEntity<>(policyService.updatePolicy(id, policyDTO), HttpStatus.OK);
    }

    /**
     * Deletes a policy by its ID.
     *
     * @param id The ID of the policy to delete.
     * @return A ResponseEntity with a status code of NO_CONTENT (204) if the policy is successfully deleted.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePolicy(@PathVariable Long id) {
        policyService.deletePolicy(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}