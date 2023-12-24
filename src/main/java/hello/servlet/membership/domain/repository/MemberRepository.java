package hello.servlet.membership.domain.repository;

import hello.servlet.membership.domain.member.Member;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 1L;

    //싱글톤 : 객체가 프로그램 내부에서 단 1개만 생성됨을 보장
    private static final MemberRepository memberRepository = new MemberRepository();
    private MemberRepository() {}
    public static MemberRepository getInstance() {
        return memberRepository;
    }

    //회원 저장
    public Member save(Member member) {
        member.setId(sequence++);
        store.put(member.getId(), member);
        return member;
    }

    //ID로 회원 검색
    public Member findById(Long id) {
        return store.get(id);
    }

    //모든 회원 출력
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    //테스트 용 ==> store 비우기
    public void clearStore() {
        store.clear();
    }

}
