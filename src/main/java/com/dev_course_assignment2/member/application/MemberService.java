package com.dev_course_assignment2.member.application;

import com.dev_course_assignment2.global.rsData.RsData;
import com.dev_course_assignment2.member.domain.Member;
import com.dev_course_assignment2.member.in.dto.MemberCreateRequest;
import com.dev_course_assignment2.member.in.dto.MemberCreateResponse;
import com.dev_course_assignment2.member.out.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public RsData<MemberCreateResponse> createMember(MemberCreateRequest request) {
        if (memberRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
        }

        Member member = new Member(
                request.email(),
                passwordEncoder.encode(request.password()),
                request.nickname()
        );

        return new RsData<>("201-1","회원가입이 완료되었습니다.",MemberCreateResponse.from(memberRepository.save(member)));
    }
}
