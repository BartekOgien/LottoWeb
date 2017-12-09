package com.application.lotto.repository;

import com.application.lotto.model.DrawNumber;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface DrawNumbersDao extends CrudRepository<DrawNumber, Integer>, PagingAndSortingRepository<DrawNumber,
        Integer> {

    DrawNumber findDrawNumberByDrawId(int drawId);

    List<DrawNumber> findAll();

}
