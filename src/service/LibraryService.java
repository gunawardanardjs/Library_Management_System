package service;

import model.Library;
import model.LibraryItem;
import model.Member;

import java.util.HashMap;
import java.util.Map;

public class LibraryService {
    private final Library library;
    private final Map<String, String> borrowedItems = new HashMap<String, String>();

    public LibraryService(Library library) {
        if (library == null) {
            throw new IllegalArgumentException("Library cannot be null");
        }
        this.library = library;
    }

    public void borrowItem(String memberId, String itemId) {
        Member member = library.getMember(memberId);
        if (member == null) {
            throw new IllegalArgumentException("Member not found");
        }

        LibraryItem item = library.getItem(itemId);
        if (item == null) {
            throw new IllegalArgumentException("Item not found");
        }
        if (item.isBorrowed()) {
            throw new IllegalStateException("Item is already borrowed");
        }
        if (member.getBorrowedCount() >= member.getMaxBorrowLimit()) {
            throw new IllegalStateException("Member has reached the borrowing limit");
        }

        item.borrowItem();
        member.incrementBorrowedCount();
        borrowedItems.put(itemId, memberId);
    }

    public void returnItem(String memberId, String itemId) {
        Member member = library.getMember(memberId);
        if (member == null) {
            throw new IllegalArgumentException("Member not found");
        }

        LibraryItem item = library.getItem(itemId);
        if (item == null) {
            throw new IllegalArgumentException("Item not found");
        }
        if (!memberId.equals(borrowedItems.get(itemId))) {
            throw new IllegalStateException("This item is not borrowed by this member");
        }

        item.returnItem();
        member.decrementBorrowedCount();
        borrowedItems.remove(itemId);
    }
}
