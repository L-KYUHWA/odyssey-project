package com.backgom.odyssey.controller;

import com.backgom.odyssey.dto.MemberDto;
import com.backgom.odyssey.service.MemberCommandService;
import com.backgom.odyssey.service.MemberQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloRestController {

    @Autowired
    MemberQueryService memberQueryService;
    @Autowired
    MemberCommandService memberCommandService;

    //Server health Check API
    @RequestMapping("hello")
    public String hello() {
        return "Hello Odyssey Service";
    }

    @RequestMapping("test")
    public String test() {
        MemberDto dto = new MemberDto();
        dto.setMemberId("AAA");
        dto.setMemberPassword("BBB");


        MemberDto dto1 = new MemberDto();
        dto1.setMemberId("AAACCC");
        dto1.setMemberPassword("BBBDDD");

        memberCommandService.saveMember(dto);
        memberCommandService.saveMember(dto1);
        memberQueryService.findAllMembers();
        return null;
    }

}
