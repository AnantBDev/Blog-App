package com.anantbhardwaj.blog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.anantbhardwaj.blog.repositories.UserRepository;

@SpringBootTest
class BlogAppApisApplicationTests {

	@Autowired
	private UserRepository userRepo;
	
	@Test
	void contextLoads() {
	}

	@Test
	public void repoTest(){
		//userRepo is an interface but is autowired meaning object is made. this object is made as a 
		//result of dynamic classes implementing interface created only during runtime
		String classname=this.userRepo.getClass().getName();
		String pckName=this.userRepo.getClass().getPackageName();
		System.out.println(classname);
		System.out.println(pckName);
	}
}
