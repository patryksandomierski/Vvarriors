package com.oliwa.web;

import com.oliwa.domain.Customer;
import com.oliwa.domain.CustomerRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    @Autowired
    CustomerRepository repository;

    @RequestMapping("/findall")
    public String findAll() {
        StringBuilder result = new StringBuilder("<html>");

        for (Customer cust : repository.findAll()) {
            result.append("<div>").append(cust.toString()).append("</div>");
        }

        return result + "</html>";
    }

    @RequestMapping("/createRandom")
    public String createRandom() {
        repository.save(new Customer(RandomStringUtils.randomAlphabetic(6), RandomStringUtils.randomAlphabetic(6)));
        return findAll();
    }
}
