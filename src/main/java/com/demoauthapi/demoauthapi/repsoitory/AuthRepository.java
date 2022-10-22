package com.demoauthapi.demoauthapi.repsoitory;

import com.demoauthapi.demoauthapi.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<Member, Long> {

}
