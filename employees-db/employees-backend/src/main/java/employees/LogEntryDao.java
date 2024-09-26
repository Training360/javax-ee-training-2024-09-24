package employees;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class LogEntryDao {

    @PersistenceContext
    private EntityManager em;

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void save(LogEntry logEntry) {
        em.persist(logEntry);
    }
}
