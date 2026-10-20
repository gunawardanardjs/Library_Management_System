package model;

public abstract class LibraryItem {
    private String id;
    private String title;
    private boolean isBorrowed;

    public LibraryItem(String id, String title) {
        validateText(id, "Id");
        validateText(title, "Title");
        this.id = id;
        this.title = title;
        this.isBorrowed = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        validateText(id, "Id");
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        validateText(title, "Title");
        this.title = title;
    }

    private static void validateText(String value, String label) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(label + " cannot be blank");
        }
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void borrowItem() {
        if (isBorrowed) {
            throw new IllegalStateException("Item is already borrowed");
        }
        this.isBorrowed = true;
    }

    public void returnItem() {
        if (!isBorrowed) {
            throw new IllegalStateException("Item is not borrowed");
        }
        this.isBorrowed = false;
    }

    public double calculateLateFee(int daysLate) {
        if (daysLate < 0) {
            throw new IllegalArgumentException("Days late cannot be negative");
        }
        return daysLate * 10.0; // default rate
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{id='" + id + "', title='" + title + "', borrowed=" + isBorrowed + "}";
    }
}