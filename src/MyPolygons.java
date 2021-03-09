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
    public void prepend(Object data_)
    {
        reset();
        Node temp = new Node(data_);
        temp.setNext(current);
        temp.setPrev(sentinel);
        current.setPrev(temp);
        sentinel.setNext(temp);
        reset();
        size++;
    }

    public void append(Object data_)
    {
        reset();
        Node temp = new Node(data_);
        temp.setNext(sentinel);
        temp.setPrev(sentinel.getPrev());
        sentinel.getPrev().setNext(temp);
        sentinel.setPrev(temp);
        reset();
        size++;
    }

    public void insert(Object data_)
    {
        Node temp = new Node(data_);
        temp.setNext(current);
        temp.setPrev(current.getPrev());
        current.getPrev().setNext(temp);
        current.setPrev(temp);
        size++;
    }

    public void insertInOrder(Object data_)
    {
        reset();
        if (current == sentinel)
        {
            prepend(data_);
        }
        else
        {
            Polygon temp = (Polygon) data_;
            while (temp.ComesBefore(current.getData()))
            {
                if (step() == -1)
                {
                    append(data_);
                    return;
                }
            }
            insert(data_);
        }
    }

    public int step()
    {
        // If current is sentinel then the list is empty and it cannot step

        current = current.getNext();
        // If after the step the current has become the sentinel, the list has reached the end. Step again to be on the first item and return -1
        if (current == sentinel)
        {
            reset();
            return -1;
        }

        return 0;
    }

    public void reset()
    {
        current = sentinel.getNext();
    }

    public Object take()
    {
        reset();
        if (size > 0)
        {
            Object temp = current.getData();
            current.getNext().setPrev(sentinel);
            sentinel.setNext(current.getNext());
            current.setNext(null);
            current.setPrev(null);
            reset();
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
