package si.kisek.springbootexample.services;

import org.springframework.stereotype.Service;
import si.kisek.springbootexample.model.Owner;
import si.kisek.springbootexample.model.OwnerRepository;
import si.kisek.springbootexample.utilities.Json;

import javax.inject.Inject;
import java.util.Collection;

    @Service
    public class OwnerServiceImpl implements OwnerService {

        /* repository for owner data base */
        private OwnerRepository repository;

        @Inject
        public void setRepository(OwnerRepository repository) {
            this.repository = repository;
        }


        @Override
        public Collection<Owner> getAllOwners() {
            return repository.findAll();
        }


        public void addOwner(String body) {
            Owner owner = Json.fromJson(body, Owner.class);
            repository.save(owner);
        }

        public Owner getOwnerWithId(Long id){
            return repository.findById(id).get();
        }

        public Collection<Owner> findOwnerWithName(String name){
            return repository.findByName(name);
        }

        public Owner updateOwnerFromDB(Long id, Owner owner) {
            Owner currentOwner = repository.findById(id).get();
            currentOwner.setName(owner.getName());
            repository.save(currentOwner);
            return currentOwner;
        }

        public void deleteOwnerWithId(Long id){
            repository.deleteById(id);
        }

        public void deleteAllOwners(){
            repository.deleteAll();
        }
    }

