package org.soumia.accountservice.clients;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.soumia.accountservice.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@FeignClient(name ="CUSTOMER-SERVICE")
public interface CustomerRestClient {

    @GetMapping("/customers/{id}")
    @CircuitBreaker(name="customerService", fallbackMethod = "getDefaultCustomer")
    Customer findCustomerById(@PathVariable Long id);

    @GetMapping("/customers")
    @CircuitBreaker(name="customerService", fallbackMethod = "getAllCustomers")
    List<Customer> findAllCustomers();

    default Customer getDefaultCustomer(Long id, Exception exception) {
        Customer customer = new Customer();
        customer.setId(id);
        customer.setFirstName("Not Available");
        customer.setEmail("Not Available");
       return customer;
    }

    default List<Customer> getAllCustomers(Exception exception) {
        return List.of();
    }
}
