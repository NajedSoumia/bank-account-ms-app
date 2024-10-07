package org.soumia.accountservice;

import org.soumia.accountservice.clients.CustomerRestClient;
import org.soumia.accountservice.entities.BankAccount;
import org.soumia.accountservice.enums.AccountType;
import org.soumia.accountservice.repository.BankAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;


import java.util.UUID;


@SpringBootApplication
@EnableFeignClients
public class AccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(BankAccountRepository bankAccountRepository,
                                        CustomerRestClient customerRestClient) {
        return args -> {
                    customerRestClient.findAllCustomers().forEach(c->{
                        BankAccount account1 =BankAccount.builder()
                                .accountId(UUID.randomUUID().toString())
                                .balance(Math.random()*1000)
                                .currency("Euro")
                                .type(AccountType.CURRENT_ACCOUNT)
                                .customerId(c.getId())
                                .build();
                        BankAccount account2 =BankAccount.builder()
                                .accountId(UUID.randomUUID().toString())
                                .balance(Math.random()*1000)
                                .currency("Euro")
                                .type(AccountType.CURRENT_ACCOUNT)
                                .customerId(c.getId())
                                .build();
                        bankAccountRepository.save(account1);
                        bankAccountRepository.save(account2);
                    });





        };
    }

}
