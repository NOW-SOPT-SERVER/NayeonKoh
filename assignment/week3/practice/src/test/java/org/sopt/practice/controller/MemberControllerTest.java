package org.sopt.practice.controller;

import io.restassured.RestAssured;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.sopt.practice.domain.Part;
import org.sopt.practice.repository.MemberRepository;
import org.sopt.practice.service.MemberService;
import org.sopt.practice.service.dto.MemberCreateRequest;
import org.sopt.practice.settings.ApiTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public class MemberControllerTest extends ApiTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Nested
    @DisplayName("create member test")
    public class CreateMember {

        @Test
        @DisplayName("success test")
        public void createMemberSuccess() throws Exception {
            // given
            // var을 사용하는 이유: 자바의 타입 추론 -> 코드 가독성
            final var request = new MemberCreateRequest("name", Part.IOS, 20);

            // when
            final var response = RestAssured
                    .given()
                    // TODO: log all이 왜 두번 들어가지
                    // TODO: 뜯어보기
                    .log().all()
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .body(request)
                    .when()
                    .post("/api/v1/member")
                    .then().log().all().extract();

            // then
            // TODO: Junit이 아닌 AssertJ를 사용하는 이유
            Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
        }
    }
}
