package linked_queue;

public interface Queue {
//    /*public*/
//    Queue makeCopy();

    void enqueue(Object element);

    void clear();

    /*public*/ Object dequeue();

    /*public*/ Object element();

    /*public*/ int size();

    /*public*/ boolean isEmpty();
}
