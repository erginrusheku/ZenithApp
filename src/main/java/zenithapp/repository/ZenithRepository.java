package zenithapp.repository;

import org.springframework.stereotype.Repository;
import zenithapp.model.Zenith;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ZenithRepository extends JpaRepository<Zenith, Long> {
}
