package com.bbangduck.service;

import java.util.List;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bbangduck.domain.Onetoone;
import com.bbangduck.domain.OnetooneRepository;

import lombok.RequiredArgsConstructor;

// 기능을 정의할 수 있고 트랜잭션 관리할 수 있는 Service
// 레포지터리는 데이터베이스의 데이터를 insert하거나 가져오거나 하는것
// 서비스는 데이터를 가져오고 다른데서도 가져오고, 
// 여러개의 데이터베이스의 쿼리를 실행하면서 어떤 기능을 만들어내는게 서비스
// 그거 전체를 롤백시킨다던지 다시 커밋시키는 것
// 예를 들어, 송금이라는 함수가 있으면
// 레포지토리에 여러개의 함수를 실행하고 그게 다 잘 돌아가면 커밋하고
// 안되면 다시 롤백하는 것

@RequiredArgsConstructor
@Service
public class OnetooneService {
	
	private final OnetooneRepository oneRepo;
	
	// 트랜잭션 관리
	@Transactional // 서비스 함수가 종료될 때 여러번 실행되다 실패되면 롤백, 성공하면 커밋됨
	public Onetoone save(Onetoone one) {
		return oneRepo.save(one);
	}
	
	
	// JPA는 변경감지라는 내장 기능이 있음
	// 어떤 엔티티가 변경된 걸 JPA에서 계속 지켜보고 있음
	// readonly가 걸려있으면 이 기능이 비활성화 되어서 내부 연산을 줄여줌
	// update시 정합성을 유지해줌, insert의 유령데이터현상 못막음(팬텀현상)
	// 즉 update 되는 정보가 반영되지 않게 막아줌(insert는 반영됨)
	@Transactional(readOnly =true) 
	public Onetoone select(Long id) {
		return oneRepo.findById(id)
				.orElseThrow(()-> new IllegalArgumentException("id를 확인해주세요!"));
	}
	
	@Transactional(readOnly =true) 
	public List<Onetoone> selectAll(){
		return oneRepo.findAll();
	}
	
	@Transactional
	public Onetoone modify(Long id, Onetoone one) {
		// 더티 체킹 update
		// 내가 데이터베이스의 실제 엔티티를 가져오면 이 값이 영속화가 됨 
		// 스프링 내부 메모리 공간에 따로 두고 있는 것을 말함.
		// 영속화된 데이터를 set해주는 등으로 바꿔치기 할 수 있음.
		// 영속화된 것은 영속성 컨텍스트에 보관됨
		
		Onetoone oneEntity = oneRepo.findById(id)
				.orElseThrow(()-> new IllegalArgumentException("id를 확인해주세요!")); 
		oneEntity.setQnaContent(one.getQnaContent());
		oneEntity.setQnaTitle(one.getQnaTitle());
		oneEntity.setQnaState(one.getQnaState());
		
		return oneEntity;
	}
	
	// 함수 종료 -> 트랜잭션 종료 -> 영속화 되어 있는 데이터를 DB로 갱신시킴(Flush) => 이게 커밋됨 ---> 이런 과정을 더티체킹이락고 함
	
	
	@Transactional
	public String delete(Long id) {
		oneRepo.deleteById(id); 
		return "ok";
	}
	
	

}
