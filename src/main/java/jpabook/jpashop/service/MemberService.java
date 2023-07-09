package jpabook.jpashop.service;

import jpabook.jpashop.entity.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    // 1. 단점 - 테스트 코드 작성 시 변경 불가, private이고 접근할 방법이 없음
//    @Autowired
//    private MemberRepository memberRepository;


    // 2. 단점 - setter injection : 다른 곳에서 setter를 통해 변경해버릴 가능성 존재
    // 장점 - TEST 코드 작성시 mock을 직접 주입 가능
    // 개발 시 중간에 이를 set할 일이 없음 => 처음에만 set이 필요, 이후는 불필요
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    // 3. 생성자 주입 (권장) => lombok @RequiredArgsConstructor
//    private final MemberRepository memberRepository;
//    @Autowired
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    // 회원 가입
    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다");
        }
    }

    //회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    // 회원 한명 조회
    public Member findOne(Long id) {
        return memberRepository.find(id);
    }
}
