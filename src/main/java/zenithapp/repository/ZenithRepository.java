package zenithapp.repository;

import zenithapp.model.ZenithDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZenithRepository  extends JpaRepository<ZenithDTO, Long> {
}
