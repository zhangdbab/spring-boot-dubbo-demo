package com.sans.provider.controler;

import com.sans.provider.entity.Person;
import com.sans.provider.jpa.PersonRepository;
import org.apache.dubbo.config.support.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProviderControler {
    @Autowired
    private PersonRepository personRepository ;

    @RequestMapping("/findall")
    List<Person> personList(){
     return    personRepository.findAll();
    }

    @RequestMapping("/finByID")
    Person personList(@RequestParam String ID){
        return    personRepository.findByID(ID);
    }
}
