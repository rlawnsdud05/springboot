package com.bbangduck.web;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.bbangduck.domain.Onetoone;
import com.bbangduck.service.OnetooneService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

// 단위 테스트
// 컨트롤러만 테스트함(컨트롤러 관련 로직만 띄우기)
// 예를 들어, Controller, Filter, ControllerAdvice 등이 메모리에 같이 뜸

// @WebMvcTest : 메모리에 위의 컨트롤러를 위한 객체들만 메모리에 뜸

// 파일을 스프링 환경으로 확장하고 싶다면, 
// @ExtendWith(SpringExtension.class) 라는 어노테이션을 붙여야 함
// JUnit 테스트를 할 때, 어떤 파일이 스프링 환경에서 테스트를 하고 싶으면 위의 어노테이션을 꼭 붙여줘야 함.
// @WebMvcTest 안에 이미 @ExtendWith(Spring~)이 붙어 있음(컨트롤 + 마우스 타고 들어가면 보임)

// JUnit4에서는 보통 @RunWith(SpringRunner.class) 라는 어노테이션을 같이 붙여줬어야 했으나,
// JUnit5를 사용할 때는 붙이지 않아도 됨
// Runwith(~)도 스프링 환경으로 확장하는 방법. 
// (JUnit4에서는 이 어노테이션이 WebMvcTest 어노테이션 안에 없었기 때문)
// 하지만 JUnit5의 경우 @ExtendWith(SpringExtension.class)이 있기 때문에 안써줘도 된다...!

@Slf4j
@WebMvcTest
public class OnetooneControllerUnitTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	// @Mock로 해주면 어차피 스프링 환경에 메모리가 안뜸(모키토 환경에만 뜸)
	// @MockBean 사용
	// IoC 환경에 bean 등록해줌
	// mock로 가짜를 만들어 올림
	@MockBean
	private OnetooneService oneService;

	// BDDMockito 패턴 given, when, then
	@Test
	public void save_테스트() throws Exception {
		// given (테스트를 하기 위한 준비)
		// 오브젝트를 스트링으로 바꾸는 함수
		Onetoone one = new Onetoone(null, "제목", "글쓴이", "content", null, null, null);
		String content = new ObjectMapper().writeValueAsString(new Onetoone(null, "제목", "글쓴이", "content", null, null, null));
		when(oneService.save(one)).thenReturn(new Onetoone(null, "제목", "글쓴이", null, null, null, null));
		
		// when (테스트 실행)
		ResultActions resultAction = mockMvc.perform(post("/onetoone")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(content)
				.accept(MediaType.APPLICATION_JSON_UTF8));
	
		// then(검증)
		resultAction
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.qna_author").value("글쓴이"))
			.andDo(MockMvcResultHandlers.print());
	}

	
}
