package co.kr.easylogin.easylogin.kakao.repository;

import co.kr.easylogin.easylogin.kakao.domain.KakaoBizApp;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KakaoBizAppRepository extends JpaRepository<KakaoBizApp, Long> {
    Optional<KakaoBizApp> findByAppId(Long appId);
}
