package co.kr.easylogin.easylogin.kakao.repository;

import co.kr.easylogin.easylogin.kakao.domain.KakaoApp;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KakaoAppRepository extends JpaRepository<KakaoApp, Long> {
    Optional<KakaoApp> findByAppId(Long appId);
}
