package hello.servlet.membership.domain.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import hello.servlet.membership.domain.member.Member;
import java.util.List;

class MemberRepositoryTest {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void clear() {
        memberRepository.clearStore();
    }

    @Test
    @DisplayName("회원 저장")
    public void save() throws Exception {
        //given
        Member member = new Member("kim", 19);

        //when
        Member savedMember = memberRepository.save(member);

        //then
        Member findMember = memberRepository.findById(savedMember.getId());
        Assertions.assertThat(savedMember).isEqualTo(findMember);
    }

    @Test
    @DisplayName("모든 회원 정보 출력")
    public void findAll() throws Exception {
        //given
        Member member1 = new Member("kim", 19);
        Member member2 = new Member("lee", 40);

        memberRepository.save(member1);
        memberRepository.save(member2);

        //when
        List<Member> result = memberRepository.findAll();

        //then
        Assertions.assertThat(result.size()).isEqualTo(2);
        Assertions.assertThat(result).contains(member1, member2);

    }
}