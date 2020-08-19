package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public class MemberService { // 서비스 내부 메서드는 비즈니스와 관련된 용어를 사용하는 것이 좋음

  private final MemberRepository memberRepository;

  @Autowired
  public MemberService(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  public Long join(Member member) {
    // 중복 회원 입력 거부
    validateDuplicatedMember(member);
    memberRepository.save(member);
    return member.getId();
  }

  private void validateDuplicatedMember(Member member) {
    memberRepository
        .findByName(member.getName())
        .ifPresent(
            m -> {
              throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
  }

  public List<Member> findMembers() {
    return memberRepository.findAll();
  }

  public Optional<Member> findOne(Long memberId) {
    return memberRepository.findById(memberId);
  }
}
