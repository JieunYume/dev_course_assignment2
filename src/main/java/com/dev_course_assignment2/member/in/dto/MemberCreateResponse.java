package com.dev_course_assignment2.member.in.dto;

import com.dev_course_assignment2.member.domain.Member;

import java.time.LocalDateTime;

public record MemberCreateResponse(
        Long id,
        String email,
        String nickname,
        LocalDateTime createdDate
) {
    public static MemberCreateResponse from(Member member) {
        return new MemberCreateResponse(
                member.getId(),
                member.getEmail(),
                member.getNickname(),
                member.getCreatedDate()
        );
    }
}
