package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller // 어노테이션 동작 원리: 스프링 컨테이너에서 어노테이션 클래스를 객체로 들고 있음, 이를 스프링 빈(Bean)이라고 함
public class MemberController {

  private final MemberService memberService;

  @Autowired // autoWired 어노테이션이 생성자가 호출될 때 매개변수 클래스의 인스턴스를 자동으로 연결함
  public MemberController(MemberService memberService) {
    this.memberService = memberService;
    System.out.println("memberService = " + memberService.getClass());
  }

  @GetMapping("/members/new")
  public String createForm() {
    return "members/createMembersForm";
  }

  @PostMapping("/members/new")
  public String create(MemberForm form) {
    Member member = new Member();
    member.setName(form.getName());

    memberService.join(member);

    return "redirect:/";
  }

  @GetMapping("/members")
  public String list(Model model) {
    List<Member> members = memberService.findMembers();
    model.addAttribute("members", members);
    return "members/memberList";
  }
}
