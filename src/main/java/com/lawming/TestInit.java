package com.lawming;

import com.lawming.domain.item.Item;
import com.lawming.domain.member.Member;
import com.lawming.domain.repository.ItemRepository;
import com.lawming.domain.repository.MemberRepository;
import com.lawming.web.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestInit {

    private final ItemRepository itemRepository;
    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @PostConstruct
    public void init() {

        Member member1 = Member.createMember("test1",
                "엠도",
                "qwe123",
                "test1@naver.com",
                "010-1234-5678");
        Member member2 = Member.createMember("test2",
                "쫀지",
                "qwe123",
                "test2@naver.com",
                "010-5678-1234");

        memberService.join(member1);
        memberService.join(member2);

        Item item1 = Item.createItem("Seoul", 100001, member1);
        Item item2 = Item.createItem("Seoul", 100002, member2);


        itemRepository.save(item1);
        itemRepository.save(item2);
    }
}
