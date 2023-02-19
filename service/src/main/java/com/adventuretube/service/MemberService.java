package com.adventuretube.service;

import com.adventuretube.model.Member;
import com.adventuretube.repo.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

//@Component
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @GetMapping
    public List<Member> getMember(){
         return memberRepository.findAll();
    }

    public void addNewMember(Member member) {
        Optional<Member> memberByEmail =  memberRepository.findMemberByEmail(member.getEmail());
        if(memberByEmail.isPresent()){
            throw new IllegalStateException("email is taken");
        }
        memberRepository.save(member);
        System.out.println(member);
    }

    public void deleteMember(Long memberId) {
      boolean exists = memberRepository.existsById(memberId);
      if(!exists){
          throw new IllegalStateException("member with id " + memberId + "does not exists");
      }
      memberRepository.deleteById(memberId);
    }


    @Transactional
    public void updateMember(Long memberId, String name, String email) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalStateException( "Member with id " + memberId + "does not exist"));

        if(name != null && name.length() > 0  &&
           !Objects.equals(member.getName(),name)){
            member.setName(name);
        }

        if(email != null && email.length() > 0 &&
          !Objects.equals(member.getEmail(),email)){
            Optional<Member> memberOptional = memberRepository.findMemberByEmail(email);
            if(memberOptional.isPresent()){
                throw new IllegalStateException( "email " + email + "has been taken ");
            }

            member.setEmail(email);
        }
    }
}
