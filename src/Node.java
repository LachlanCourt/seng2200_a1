public class Node
{
    private Object data;
    private Node next;
    private Node prev;

    public Node()
    {
        data = null;
    }

    public Node(Object data_)
    {
        data = data_;
    }

    public Object getData()
    {
        return data;
    }

    public void setData(Object data_)
    {
        data = data_;
    }

    public Node getNext()

    {
        return next;
    }

    public void setNext(Node next_)
    {
        next = next_;
    }

    public Node getPrev()
    {
        return prev;
    }

    public void setPrev(Node prev_)
    {
        prev = prev_;
    }
}
