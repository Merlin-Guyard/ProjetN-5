package com.mg.warning.person;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PersonRepositoryTest {

//    @Mock
//    private PersonRepository personRepository = mock(PersonRepository.class);

    @InjectMocks
    private PersonRepository personRepository = new PersonRepository();


    @Test
    void testfindByFirstAndLastName() {

        Person person = new Person();
        person.setFirstName("Bobby");
        person.setLastName("Dupont");
        personRepository.save(person);

        List<Person> persons = new ArrayList<>(personRepository.findByFirstAndLastName("Bobby", "Dupont"));
        Person person2 = new Person();
        person2 = persons.get(0);
        assertThat(person2.getFirstName()).isEqualTo("Bobby");
    }

}