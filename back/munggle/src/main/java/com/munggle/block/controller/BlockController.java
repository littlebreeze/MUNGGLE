package com.munggle.block.controller;

import com.munggle.block.service.BlockSerivce;
import com.munggle.domain.model.entity.User;
import com.munggle.user.dto.UserListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/blocks")
public class BlockController {

    private final BlockSerivce blockSerivce;

    @GetMapping()
    public List<UserListDto> getBlockUserList(@AuthenticationPrincipal User principal) {
        Long fromUserId = principal.getId();
        return blockSerivce.getBlockUserLsit(fromUserId);
    }

    @PostMapping("/{toUserId}")
    public void blockUser(@PathVariable Long toUserId,
                          @AuthenticationPrincipal User principal) {
        Long fromUserId = principal.getId();
        blockSerivce.blockUser(fromUserId, toUserId);
    }
}
