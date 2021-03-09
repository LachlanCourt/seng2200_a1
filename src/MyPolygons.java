public class MyPolygons
{
    /*
    prepend items into the start of the list (current item will be the new first in list),
• append items into the end of the list (current item will be the new first in list),
• insert before a specified (current) item,
• step to the next item (making it the current item),
• reset the current item variable to the start of your list, and,
• take (then remove) an item from the head of the list.
Note: You may not have to use all the above methods in this assi
test
     */
    private Node sentinel = new Node();
    private Node current = sentinel;
    int size;

    public MyPolygons()
    {
        size = 0;
        sentinel.setNext(sentinel);
        sentinel.setPrev(sentinel);
    }

    /**
     *
     * @param data_
     */
    public void prepend(Polygon data_)
    {
        currentToHead();
        Node temp = new Node(data_);
        temp.setNext(current);
        temp.setPrev(sentinel);
        current.setPrev(temp);
        sentinel.setNext(temp);
        currentToHead();
        size++;
    }

    public void append(Polygon data_)
    {
        currentToHead();
        Node temp = new Node(data_);
        temp.setNext(sentinel);
        temp.setPrev(sentinel.getPrev());
        sentinel.getPrev().setNext(temp);
        sentinel.setPrev(temp);
        currentToHead();
        size++;
    }

    public void insert(Polygon data_)
    {
        Node temp = new Node(data_);
        temp.setNext(current);
        temp.setPrev(current.getPrev());
        current.getPrev().setNext(temp);
        current.setPrev(temp);
        size++;
    }

    public void insertInOrder(Polygon data_)
    {
        currentToHead();
        if (current == sentinel)
        {
            prepend(data_);
        }
        else
        {
            Polygon temp = data_;
            while (temp.ComesBefore(current.getData()))
            {
                if (!hasNext())
                {
                    next();
                    append(data_);
                    return;
                }
                next();
            }
            insert(data_);
        }
    }

    public Polygon next()
    {
        // If current is sentinel then the list is empty and it cannot step

        current = current.getNext();
        // If after the step the current has become the sentinel, the list has reached the end. Step again to be on the first item and return -1
        if (current == sentinel)
        {
            currentToHead();
        }

        return (Polygon) current.getData();
    }

    public boolean hasNext()
    {
        if (current.getNext() == sentinel)
        {
            return false;
        }
        return true;
    }

    public void currentToHead()
    {
        current = sentinel.getNext();
    }

    public Polygon removeFromHead()
    {
        currentToHead();
        if (size > 0)
        {
            Polygon temp = current.getData();
            current.getNext().setPrev(sentinel);
            sentinel.setNext(current.getNext());
            current.setNext(null);
            current.setPrev(null);
            currentToHead();
            size--;
            return temp;

        }
        else
        {
            return null;
        }
    }

    public int getSize()
    {
        return size;
    }
}
