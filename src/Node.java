public class Node
{
    private Polygon data;
    private Node next;
    private Node prev;

    public Node()
    {
        data = null;
    }

    public Node(Polygon data_)
    {
        data = data_;
    }

    public Polygon getData()
    {
        return data;
    }

    public void setData(Polygon data_)
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
