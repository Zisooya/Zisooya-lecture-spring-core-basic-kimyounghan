package hello.core.member;

public class MemberServiceImpl implements MemberService{

    // 1. 인터페이스와 구현체를 MemberServiceImpl이 직접 주입하던 방식.
//    private final MemberRepository memberRepository = new MemoryMemberRepository();

    // 2-1. AppConfig에서 주입하도록 변경.
    private final MemberRepository memberRepository;

    // 2-2. 생성자를 통해 memberRepository의 구현체로 무엇이 들어갈 지를 결정.
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {

        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {

        return memberRepository.findById(memberId);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
