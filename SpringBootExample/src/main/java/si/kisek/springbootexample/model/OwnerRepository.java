package si.kisek.springbootexample.model;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    List<Owner> findByName(String name);
}