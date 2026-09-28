package com.dev_course_assignment2.member.in;

import com.dev_course_assignment2.global.rsData.RsData;
import com.dev_course_assignment2.member.application.MemberService;
import com.dev_course_assignment2.member.in.dto.MemberCreateRequest;
import com.dev_course_assignment2.member.in.dto.MemberCreateResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class ApiV1MemberController {
    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<RsData<MemberCreateResponse>> createMember(
            @Valid @RequestBody MemberCreateRequest request
    ) {
        RsData<MemberCreateResponse> result = memberService.createMember(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(result);
    }
}
