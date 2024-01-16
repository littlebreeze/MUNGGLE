package com.munggle.member.service;

import com.munggle.domain.model.entity.Member;
import com.munggle.member.dto.MemberCreateDto;
import com.munggle.member.mapper.MemberMapper;
import com.munggle.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return memberRepository.findByUsernameAndIsDeletedFalse(username)
                .orElseThrow(() -> new NoSuchElementException("회원을 찾을 수 없습니다."));
    }

    @Override
    @Transactional
    public void joinMember(MemberCreateDto memberCreateDto) {
        Member newMember = MemberMapper.toEntity(memberCreateDto);
        memberRepository.save(newMember);
    }

    @Override
    public Member findMemberById(Long id) {
        return memberRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new NoSuchElementException("회원을 찾을 수 없습니다."));
    }
}
