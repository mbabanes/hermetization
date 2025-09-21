package btn.jmt.hermetization.service.document;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

interface DocumentRepository extends JpaRepository<DocumentEntity, String> {

  Optional<DocumentEntity> findByIdAndProcessNumber(String id, String processNumber);

  List<DocumentEntity> findAllByProcessNumber(String processNumber);

  long countByProcessNumber(String id);
}
