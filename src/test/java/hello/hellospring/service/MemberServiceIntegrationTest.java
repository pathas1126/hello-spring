package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest // 스프링 컨테이너와 테스트를 함께 실행, 실제 스프링을 띄워서 테스트 진행
@Transactional // Test 클래스에 작성하는 경우, 각가의 테스트 후 데이터베이스에서 커밋을 하지 않고 롤백을 함
class MemberServiceIntegrationTest {

  @Autowired MemberService memberService;
  @Autowired MemberRepository MemberRepository;

  // 각 테스트에 @Commit 어노테이션으로 롤백이 되지 않게 할 수 있음
  @Test
  void 회원가입() {
    // given
    Member member = new Member();
    member.setName("hello2");

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
    member1.setName("spring2");

    Member member2 = new Member();
    member2.setName("spring2");

    // when
    memberService.join(member1);
    IllegalStateException error =
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));
    assertThat(error.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
  }
}
