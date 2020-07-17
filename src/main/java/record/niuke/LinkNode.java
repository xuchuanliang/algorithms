package record.niuke;

public class LinkNode<T> {
    private T val;
    private LinkNode next;

    public LinkNode(T val, LinkNode next) {
        this.val = val;
        this.next = next;
    }

    public T getVal() {
        return val;
    }

    public LinkNode getNext() {
        return next;
    }

    public void setNext(LinkNode next) {
        this.next = next;
    }

    public void setVal(T val) {
        this.val = val;
    }


    @Override
    public String toString() {
        return "LinkNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }

    public static LinkNode init(int length) {
        LinkNode first = new LinkNode(0, getNext(1, length));
        return first;
    }

    private static LinkNode getNext(int i, int length) {
        if (i == length) {
            return new LinkNode(i, null);
        } else {
            return new LinkNode(i, getNext(i + 1, length));
        }
    }

}
