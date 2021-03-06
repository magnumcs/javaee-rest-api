package no.njm.data;

import no.njm.model.Person;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Singleton
class PersonDatabase implements PersonRepository {

    private static final int INITIAL_ID = 1;
    private final AtomicLong counter = new AtomicLong(INITIAL_ID);
    private final Map<Long, Person> persons = new HashMap<>();

    @PostConstruct
    public void init() {
        long first = counter.getAndIncrement();
        persons.put(first, new Person(first, "Isaac", "'Newton"));
        long second = counter.getAndIncrement();
        persons.put(second, new Person(second, "Charles", "Darwin"));
    }

    @Override
    public List<Person> listPersons() {
        List<Person> list = new ArrayList<>(persons.size());
        persons.entrySet().forEach(entry -> list.add(entry.getValue()));
        return list;
    }

    @Override
    public Optional<Person> getPerson(long id) {
        if (persons.containsKey(id)) {
            return Optional.of(persons.get(id));
        }
        return Optional.empty();
    }
}
