package com.example.junhyukcrudapi.member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void signUp(CreateMemberRequest createMemberRequest) {Member member = createMemberRequest.toEntity();
        memberRepository.save(member);
    }

    public MemberResponse getMember(String name) {
        Member member = memberRepository.findByName(name);
        return MemberResponse.from(member);
    }

    public void updateMember(String name, UpdateMemberRequest updateMemberRequest) {
        Member member = memberRepository.findByName(name);
        member.updateMember(updateMemberRequest.getUsername(), updateMemberRequest.getEmail());
        memberRepository.save(member);
    }

    public void deleteMember(String name) {
        Member member = memberRepository.findByName(name);
        memberRepository.delete(member);
    }
}
