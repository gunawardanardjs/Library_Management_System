package model;

public class Member {
    private String memberId;
    private String name;
    private int borrowedCount;
    private int maxBorrowLimit;

    public Member(String memberId, String name, int maxBorrowLimit) {
        validateText(memberId, "Member ID");
        validateText(name, "Name");
        if (maxBorrowLimit <= 0) {
            throw new IllegalArgumentException("Borrow limit must be greater than zero");
        }
        this.memberId = memberId;
        this.name = name;
        this.maxBorrowLimit = maxBorrowLimit;
        this.borrowedCount = 0;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        validateText(memberId, "Member ID");
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        validateText(name, "Name");
        this.name = name;
    }

    private static void validateText(String value, String label) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(label + " cannot be blank");
        }
    }

    public int getBorrowedCount() {
        return borrowedCount;
    }

    public void setBorrowedCount(int borrowedCount) {
        if (borrowedCount < 0 || borrowedCount > maxBorrowLimit) {
            throw new IllegalArgumentException("Borrowed count must be between zero and the borrow limit");
        }
        this.borrowedCount = borrowedCount;
    }

    public int getMaxBorrowLimit() {
        return maxBorrowLimit;
    }

    public void incrementBorrowedCount() {
        if (borrowedCount >= maxBorrowLimit) {
            throw new IllegalStateException("Member has reached the borrowing limit");
        }
        setBorrowedCount(this.borrowedCount + 1);
    }

    public void decrementBorrowedCount() {
        if (borrowedCount == 0) {
            throw new IllegalStateException("Member has no borrowed items");
        }
        setBorrowedCount(this.borrowedCount - 1);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{id='" + memberId + "', name='" + name + "', borrowedCount=" + borrowedCount + ", limit=" + maxBorrowLimit + "}";
    }
}