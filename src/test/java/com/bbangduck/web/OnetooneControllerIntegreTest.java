package com.bbangduck.web;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

import com.bbangduck.domain.Onetoone;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

// 통합 테스트
// 컨트롤러로 전체 스프링 내의 것들을 테스트함
// 모든 Bean들을 똑같이 IoC에 올리고 테스트 하는 것
// 통합 테스트가 더 느림. 하지만 단위 테스트에 비해서 더 안전

// @SpringBootTest : 모든 애들이 메모리에 다 뜸
// 기존의 환경에서 돌리는 환경고 동일함.
// 그럼 왜 하냐?
// 서버를 돌리지 않고도 테스트 할 수 있다는 점


// WebEnvironment.MOCK : 실제 톰켓을 올리는게 아니라 다른 톰켓을 돌려 테스트
// WebEnvironment.RANDOM_POR : 실제 톰켓으로 테스트함.

// @AutoConfigureMockMvc : mockMvc 의존성 주입해줌, mockmvc를 메모리에 띄워주는 역할
// @WebMvcTest 에서는 안써줘도 되는 이유는 그 어노테이션 안에 이미 AutoConfigureMockMvc이 포함되어 있기 때문
// @Transactional : 각각의 테스트함수가 종료될 때마다 트랜잭션을 rollback 해주는 어노테이션

@Slf4j
@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class OnetooneControllerIntegreTest {
	
	
	// @Autowired : 메모리에 떠있어야 함
	// MockMvc : perform을 써서 컨트롤러의 주소로 테스트해볼 수 있는 라이브러리
	// 이 라이브러리를 쓰려면 
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void save() {
		log.info("save_테스트 시작========================");
	}
	
	@Test
	public void save_테스트() throws Exception {
		// given (테스트를 하기 위한 준비)
		// 오브젝트를 스트링으로 바꾸는 함수
		Onetoone one = new Onetoone(null, "제목", "글쓴이", "content", null, null, null);
		String content = new ObjectMapper().writeValueAsString(new Onetoone(null, "제목", "글쓴이", "content", null, null, null));
		
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
