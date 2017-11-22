package com.application.lotto.repository;

import com.application.lotto.model.DrawNumber;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface DrawNumbersDao extends CrudRepository<DrawNumber, Integer> {
}
