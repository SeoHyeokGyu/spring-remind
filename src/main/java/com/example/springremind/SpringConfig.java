package com.example.springremind;

import com.example.springremind.repository.JdbcMemberRepository;
import com.example.springremind.repository.JdbcTemplateMemberRepository;
import com.example.springremind.repository.MemberRepository;
import com.example.springremind.repository.MemoryMemberRepository;
import com.example.springremind.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new JdbcTemplateMemberRepository(dataSource);
    }
}
