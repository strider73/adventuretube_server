package com.adventuretube.data.member;

import com.adventuretube.model.Member;
import com.adventuretube.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "api/member")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public List<Member> getMember(){
        return memberService.getMember();
    }

    @PostMapping
    public void registerNewMember(@RequestBody Member member){
        memberService.addNewMember(member);
    }

    @DeleteMapping(path = "{memberId}")
    public void deleteMember(@PathVariable("memberId") Long memberId){
        memberService.deleteMember(memberId);
    }
    
    
    @PutMapping(path = "{memberId}")
    public void updateMember(
            @PathVariable("memberId")  Long memberId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email) {
        memberService.updateMember(memberId, name, email);
    }
}
