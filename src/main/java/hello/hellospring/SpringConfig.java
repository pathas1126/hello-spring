package hello.hellospring;

import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

  //  private DataSource dataSource;
  private EntityManager em;

  @Autowired
  public SpringConfig(EntityManager em) {
    this.em = em;
  }

  //  public SpringConfig(DataSource dataSource) {
  //    this.dataSource = dataSource;
  //  }

  @Bean
  public MemberService memberService() {
    return new MemberService(memberRepository()); // 의존성 주입
  }

  @Bean
  public MemberRepository memberRepository() {
    return new JpaMemberRepository(em);
  }
}
