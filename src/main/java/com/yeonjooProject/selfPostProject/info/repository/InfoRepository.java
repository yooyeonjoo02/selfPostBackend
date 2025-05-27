package com.yeonjooProject.selfPostProject.info.repository;

import com.yeonjooProject.selfPostProject.info.entity.Info;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InfoRepository extends JpaRepository<Info, Long> {
    List<Info> findByCategoryId(Long categoryId);
}
