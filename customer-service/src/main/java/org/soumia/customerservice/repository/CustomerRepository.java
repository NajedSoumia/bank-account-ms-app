package org.soumia.customerservice.repository;

import org.soumia.customerservice.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository <Customer,Long> {
}
