package co.kr.easylogin.easylogin.member.repository;

import co.kr.easylogin.easylogin.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

}
