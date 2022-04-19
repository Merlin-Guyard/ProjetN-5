package com.mg.warning.person;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PersonRepositoryTest {

    @InjectMocks
    private PersonRepository personRepository = new PersonRepository();


    @Test
    void testFindByFirstAndLastName() {

        Person personToAdd = new Person();
        personToAdd.setFirstName("Bobby");
        personToAdd.setLastName("Dupont");
        personToAdd.setZip(42);
        personRepository.save(personToAdd);

        List<Person> persons = new ArrayList<>(personRepository.findByFirstAndLastName("Bobby", "Dupont"));
        Person personToCheck = new Person();
        personToCheck = persons.get(0);
        assertThat(personToCheck.getZip()).isEqualTo(personToAdd.getZip());
    }

    @Test
    void testFindByAddress() {

        Person personToAdd = new Person();
        personToAdd.setAddress("Strawberry road");
        personToAdd.setZip(42);
        personRepository.save(personToAdd);

        List<Person> persons = new ArrayList<>(personRepository.findByAddress("Strawberry road"));
        Person personToCheck = new Person();
        personToCheck = persons.get(0);
        assertThat(personToCheck.getZip()).isEqualTo(personToAdd.getZip());
    }

    @Test
    void testFindByCity() {

        Person personToAdd = new Person();
        personToAdd.setCity("Paris");
        personToAdd.setZip(42);
        personRepository.save(personToAdd);

        List<Person> persons = new ArrayList<>(personRepository.findByCity("Paris"));
        Person personToCheck = new Person();
        personToCheck = persons.get(0);
        assertThat(personToCheck.getZip()).isEqualTo(personToAdd.getZip());
    }

}