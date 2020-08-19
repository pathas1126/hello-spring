package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

  MemberService memberService;
  MemoryMemberRepository memoryMemberRepository;

  @BeforeEach
  public void beforeEach() {
    memoryMemberRepository = new MemoryMemberRepository();
    memberService = new MemberService(memoryMemberRepository);
  }

  @AfterEach // 테스트는 순서가 보장되지 않기 때문에 각 테스트 후 repository를 초기화해 주어야함
  public void afterEach() {
    memoryMemberRepository.clearStore();
  }

  @Test
  void 회원가입() {
    // given
    Member member = new Member();
    member.setName("hello");

    // when
    Long saveId = memberService.join(member);

    // then
    Member foundMember = memberService.findOne(saveId).get();
    assertThat(member.getName()).isEqualTo(foundMember.getName());
  }

  @Test
  public void 중복_회원_예외() {
    // given
    Member member1 = new Member();
    member1.setName("spring");

    Member member2 = new Member();
    member2.setName("spring");

    // when
    memberService.join(member1);
    IllegalStateException error =
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));
    assertThat(error.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    //    try {
    //      memberService.join(member2);
    //      fail();
    //    } catch (IllegalStateException error) {
    //      assertThat(error.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    //    }

    // then
  }

  @Test
  void 전체회원조회() {}

  @Test
  void 회원조회() {}
}
