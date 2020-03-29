package com.saygiselim.springboot.seed.app.member;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface MemberRepository extends CrudRepository<Member, Integer> {
    Optional<Member> findByEmail(String email);
}