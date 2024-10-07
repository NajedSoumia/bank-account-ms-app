package org.soumia.customerservice.web;

import org.soumia.customerservice.entities.Customer;
import org.soumia.customerservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class CustomerRestController {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerRestController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping("/customers")
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping("/customers/{id}")
    public Customer getCustomerById(@PathVariable Long id){
        Optional<Customer> result = customerRepository.findById(id);
        Customer customer = null;
        if (result.isPresent()){
            customer = result.get();
        }else{
            throw new RuntimeException("Did not find employee id -" + id);
        }
        return customer;
    }


}
