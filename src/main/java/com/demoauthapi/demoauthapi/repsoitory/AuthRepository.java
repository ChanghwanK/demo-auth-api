package com.demoauthapi.demoauthapi.repsoitory;

import com.demoauthapi.demoauthapi.entity.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByNickName(String email);
}
