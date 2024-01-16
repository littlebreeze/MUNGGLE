package com.munggle.member.service;

import com.munggle.domain.model.entity.Member;
import com.munggle.member.dto.MemberCreateDto;
import com.munggle.member.mapper.MemberMapper;
import com.munggle.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public void joinMember(MemberCreateDto memberCreateDto) {
        Member newMember = MemberMapper.toEntity(memberCreateDto);
        memberRepository.save(newMember);
    }
}
