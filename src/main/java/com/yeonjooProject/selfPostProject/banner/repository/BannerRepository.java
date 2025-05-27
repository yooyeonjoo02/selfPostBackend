package com.yeonjooProject.selfPostProject.banner.repository;

import com.yeonjooProject.selfPostProject.banner.entity.Banner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BannerRepository extends JpaRepository<Banner, Long> {
    // 필요한 경우 추가 쿼리 메서드 작성 가능
}
