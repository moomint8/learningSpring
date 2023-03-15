package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.Optional;
import java.util.List;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id); // 찾는 값이 없는 경우 null 반환 대신 optional로 감싸서 반환 처리
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
