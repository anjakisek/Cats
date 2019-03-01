package si.kisek.springbootexample.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import si.kisek.springbootexample.model.Cat;
import si.kisek.springbootexample.model.CatRepository;
import si.kisek.springbootexample.utilities.Json;

import javax.inject.Inject;
import java.util.Collection;
import java.util.List;

@Service
public class CatServiceImpl implements CatService {

    /* repository for cat data base */
    private CatRepository repository;

    @Inject
    public void setRepository(CatRepository repository) {
        this.repository = repository;
    }


    @Override
    public Collection<Cat> getAllCats() {
        return repository.findAll();
    }


    public void addCat(String body) {
        Cat cat = Json.fromJson(body, Cat.class);
        repository.save(cat);
    }

    public Cat getCatWithId(Long id){
        return repository.findById(id).get();
    }

    public Collection<Cat> findCatWithName(String name){
        return repository.findByName(name);
    }

    public Cat updateCatFromDB(Long id, Cat cat) {
        Cat currentCat = repository.findById(id).get();
        currentCat.setName(cat.getName());
        currentCat.setDescription(cat.getDescription());
        currentCat.setHungry(cat.getHungry());
        repository.save(currentCat);
        return currentCat;
    }

    public void deleteCatWithId(Long id){
        repository.deleteById(id);
    }

    public void deleteAllCats(){
        repository.deleteAll();
    }
}
