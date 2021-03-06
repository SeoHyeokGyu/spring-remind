package com.example.springremind.service;

import com.example.springremind.domain.Member;
import com.example.springremind.repository.MemberRepository;
import com.example.springremind.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {
    // command + shift + t - 테스트 만들어줌
    private final MemberRepository memberRepository;


    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
// 회원가입

    public Long join(Member member) {

        long start = System.currentTimeMillis();


        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();

        //command + option + v
        // controll + t - extract method

    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    //전체회원조회

    public List<Member> findMembers() {
        long start = System.currentTimeMillis();

        return memberRepository.findAll();


    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
