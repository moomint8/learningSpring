package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

// 순수한 단위 테스트(스프링 컨테이너 없이)를 하는 것이 실행 시간이 훨씬 빠름.
// 되도록 테스트는 스프링 컨테이너 없이 단위를 최대한 작게 하는 것이 대부분 좋음.
class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach  // 각 테스트 이후 실행
    public void afterEach() {
        memberRepository.clearStore();    // 메모리 초기화
    }

    @Test
    void 회원가입() {
        // given 이런 상황이 주어져서
        Member member = new Member();
        member.setName("hello");

        // when 이걸로 실행했을 때
        Long saveId = memberService.join(member);

        // then 결과가 이렇게 나와야 해
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
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
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        /*
        try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
        */

        // then

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}