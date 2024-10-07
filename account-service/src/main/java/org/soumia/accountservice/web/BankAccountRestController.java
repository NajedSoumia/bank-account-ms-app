package org.soumia.accountservice.web;

import org.soumia.accountservice.clients.CustomerRestClient;
import org.soumia.accountservice.entities.BankAccount;
import org.soumia.accountservice.model.Customer;
import org.soumia.accountservice.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class BankAccountRestController {

    private BankAccountRepository bankAccountRepository;
    private CustomerRestClient customerRestClient;

    @Autowired
    public BankAccountRestController(BankAccountRepository bankAccountRepository, CustomerRestClient customerRestClient) {
        this.bankAccountRepository = bankAccountRepository;
        this.customerRestClient = customerRestClient;
    }


    @GetMapping("/accounts")
    public List<BankAccount> getBankAccounts() {
        return bankAccountRepository.findAll();
    }

    @GetMapping("/accounts/{id}")
    public BankAccount getBankAccountById(@PathVariable String id) {
        Optional<BankAccount> result = bankAccountRepository.findById(id);

        BankAccount bankAccount = null;
        if (result.isPresent()){
            bankAccount = result.get();
        }else{
            throw new RuntimeException("Did not find employee id -" + id);
        }
        Customer customer = customerRestClient.findCustomerById(bankAccount.getCustomerId());
        bankAccount.setCustomer(customer);
        return bankAccount;
    }
}
