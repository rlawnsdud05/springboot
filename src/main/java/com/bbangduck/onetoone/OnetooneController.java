package com.bbangduck.onetoone;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OnetooneController {
	
private OnetooneRepository repo;
	
	@Autowired
	public OnetooneController(OnetooneRepository repo) {
		this.repo = repo;
	}
	
	@GetMapping(value="/onetoone")
	public List<Onetoone> getOnetooneList(){
		return repo.findAll(Sort.by("id").descending());
	}
	
	// 페이징 처리
	@GetMapping(value = "/onetoone/paging")
	public Page<Onetoone> getContactListPaging(@RequestParam int page, @RequestParam int size){
		return repo.findAll(PageRequest.of(page, size, Sort.by("id").descending()));
	}


	// 1건 추가
	// POST /contacts {name: "choi", email="", phone=""}
	@PostMapping(value = "/onetoone")
	public Onetoone addOnetoone(@RequestBody Onetoone onetoone, HttpServletResponse res) {
		
		
		if(onetoone.getQnaTitle()  == null || onetoone.getQnaTitle().equals("")) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		
		if(onetoone.getQnaAuthor() == null || onetoone.getQnaAuthor().equals("")) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		if(onetoone.getQnaContent() == null || onetoone.getQnaPwd().equals("")) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
			
		}
		if(onetoone.getQnaContent() == null || onetoone.getQnaContent().equals("")) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
			
		}
		
		onetoone.setQnaDate(new Date().getTime());
		return repo.save(onetoone);
		
		
	}
	
	// ok
	// 1건 조회
	@RequestMapping(method = RequestMethod.GET, value = "/onetoone/{id}")
	public Onetoone getOnetoone(@PathVariable int id, HttpServletResponse res) {
		Optional<Onetoone> onetoone =repo.findById(id);
		
		if(onetoone.isEmpty()) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		return onetoone.get();
		
	}
	
	// ok
	// 1건 삭제
	@DeleteMapping(value ="/onetoone/{id}")
	public boolean removeContact(@PathVariable int id, HttpServletResponse res) {
		
		Optional<Onetoone> onetoone = repo.findById(id);
		
		if(onetoone.isEmpty()) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return false;
		}
		
		repo.deleteById(id);
		return true;
	}
	
	// ok
	// 1건 수정
	// PUT /contact/1 {"name" :~~~}
	@PutMapping(value="/onetoone/{id}")
	public Onetoone modifyOnetoone(@PathVariable int id, @RequestBody Onetoone onetoone, HttpServletResponse res) {
		Optional<Onetoone> findedOnetoone = repo.findById(id);
		
		if (findedOnetoone.isEmpty()) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		
		Onetoone toUpdateOnetoone = findedOnetoone.get();
		toUpdateOnetoone.setQnaTitle(onetoone.getQnaTitle());
		toUpdateOnetoone.setQnaAuthor(onetoone.getQnaAuthor());
		toUpdateOnetoone.setQnaPwd(onetoone.getQnaPwd());
		toUpdateOnetoone.setQnaContent(onetoone.getQnaContent());

		
		return repo.save(toUpdateOnetoone);
		
	}

}
