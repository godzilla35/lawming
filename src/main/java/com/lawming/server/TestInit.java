package com.lawming.server;

import com.lawming.server.domain.Item;
import com.lawming.server.domain.Member;
import com.lawming.server.repository.ItemRepository;
import com.lawming.server.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestInit {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    @PostConstruct
    public void init() {
        Member member = Member.createMember("test1",
                "엠도",
                "qwe123",
                "test@naver.com",
                "010-1234-5678");

        memberRepository.save(member);

        Item item = Item.createItem("Seoul", 100000, member);

        itemRepository.save(item);
    }
}
