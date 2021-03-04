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
     */
    private Node sentinel = new Node();
    private Node current = sentinel;
    int size;

    public MyPolygons()
    {
        size = 0;
    }

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
        Node temp = new Node(data_);
        if (current != sentinel)
        {
            current = sentinel.getPrev();
            temp.setPrev(current);
            current.setNext(temp);
        }
        else
        {
            temp.setPrev(sentinel);
            sentinel.setNext(temp);
        }
        temp.setNext(sentinel);
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
        if (current != sentinel)
        {
            current = current.getNext();
            // If after the step the current has become the sentinel, the list has reached the end. Step again to be on the first item and return -1
            if (current == sentinel)
            {
                current = sentinel.getNext();
                return -1;
            }
        }
        return 0;
    }

    public int reset()
    {
        if (sentinel.getNext() != null)
        {
            current = sentinel.getNext();
        }
        else
        {
            current = sentinel;
            return -1;
        }
        return 0;
    }

    public Object take()
    {
        if (reset() != -1)
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
