package com.bbangduck.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bbangduck.domain.Onetoone;
import com.bbangduck.service.OnetooneService;

import lombok.RequiredArgsConstructor;


// 스프링이 빈들을 메모리에 올릴 때(IoC 컨테이너에 올릴 때)
// 생성자를 통해서 올려주는데 
// final을 걸면 @RequiredArgsConstructor가 자동으로 생성자를 만들어줌
@RequiredArgsConstructor
@RestController
public class OnetooneController {
	
	private final OnetooneService oneService;
	
	// <?> : return 할 때 어떤 경우 int일 수도 있고 string일 수도 있는데
	// ?를 넣으면 모든 타입을 다 return 할 수 있음(자바 제네릭)
	
	// ResponseEntity : return할 때 HttphStatus를 같이 보내줌

	
	// 모두 가져오기 (selectAll)
	@GetMapping("/onetoone")
	public ResponseEntity<?> findAll(){
		return new ResponseEntity<>(oneService.selectAll(), HttpStatus.OK);
	}
	
	// 저장하기
	// @RequestBody : 데이터 받을 때 json으로 받게해줌
	@PostMapping("/onetoone")
	public ResponseEntity<?> save(@RequestBody Onetoone one){
		return new ResponseEntity<>(oneService.save(one), HttpStatus.OK);
	}
	 

	// 한건 가져오기 (select)
	// @RequestBody : 데이터 받을 때 json으로 받게해줌
	@GetMapping("/onetoone/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id){
		return new ResponseEntity<>(oneService.select(id), HttpStatus.OK);
	}
	
	// 수정하기 (update)
	@PutMapping("/onetoone/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Onetoone one){
		return new ResponseEntity<>(oneService.modify(id, one), HttpStatus.OK);
	}
	
	// 삭제하기(delete)
	@DeleteMapping("/onetoone/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id){
		return new ResponseEntity<>(oneService.delete(id), HttpStatus.OK);
	}
}
