package zenithapp.repository;

import zenithapp.model.Zenith;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZenithRepository  extends JpaRepository<Zenith, Long> {
}
