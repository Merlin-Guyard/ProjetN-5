package com.mg.warning.person;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

@SpringBootTest
class PersonRepositoryTest {

    @Mock
    private PersonRepository personRepository = mock(PersonRepository.class);

//    @InjectMocks


    @Test
    void testfindByFirstAndLastName() {

        Person person = new Person();
        person.setFirstName("Bobby");
        person.setLastName("Dupont");
        personRepository.save(person);

        List<Person> persons = new ArrayList<>(personRepository.findByFirstAndLastName("Bobby", "Dupont"));
        assertThat(persons.get(0).getFirstName().equals("Bobby"));
    }

}