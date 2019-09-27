package org.service.translate.history;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TranslateHistoryRepository extends CrudRepository<TranslateHistory, Long> {

}
