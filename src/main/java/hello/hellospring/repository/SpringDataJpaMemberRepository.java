package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository
    extends JpaRepository<Member, Long>, MemberRepository {

  // SELECT M FROM MEMBER M WHERE M.NAME = ?
  @Override
  Optional<Member> findByName(String name);
}
