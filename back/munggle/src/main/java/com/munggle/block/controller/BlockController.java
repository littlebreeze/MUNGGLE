package com.munggle.block.controller;

import com.munggle.block.service.BlockSerivce;
import com.munggle.domain.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/blocks")
public class BlockController {

    private final BlockSerivce blockSerivce;

    @PostMapping("/{toUserId}")
    public void blockUser(@PathVariable Long toUserId,
                          @AuthenticationPrincipal User principal) {
        Long fromUserId = principal.getId();
        blockSerivce.blockUser(fromUserId, toUserId);
    }
}
