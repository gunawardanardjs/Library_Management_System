package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Library {
    private final Map<String, LibraryItem> items = new LinkedHashMap<String, LibraryItem>();
    private final Map<String, Member> members = new LinkedHashMap<String, Member>();

    public void addItem(LibraryItem item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        if (items.containsKey(item.getId())) {
            throw new IllegalArgumentException("An item with this ID already exists");
        }
        items.put(item.getId(), item);
    }

    public LibraryItem getItem(String id) {
        return items.get(id);
    }

    public List<LibraryItem> getItems() {
        return Collections.unmodifiableList(new ArrayList<LibraryItem>(items.values()));
    }

    public void registerMember(Member member) {
        if (member == null) {
            throw new IllegalArgumentException("Member cannot be null");
        }
        if (members.containsKey(member.getMemberId())) {
            throw new IllegalArgumentException("A member with this ID already exists");
        }
        members.put(member.getMemberId(), member);
    }

    public Member getMember(String memberId) {
        return members.get(memberId);
    }

    public List<Member> getMembers() {
        return Collections.unmodifiableList(new ArrayList<Member>(members.values()));
    }
}
