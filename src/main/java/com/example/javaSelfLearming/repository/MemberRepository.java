package com.example.javaSelfLearming.repository;

import com.example.javaSelfLearming.entity.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, Long> {
}
