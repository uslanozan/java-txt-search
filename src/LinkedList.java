import java.io.FileNotFoundException;

public class LinkedList<T extends Comparable> {  //extends inheritance için kullanılır, implements interface için
    private Node<T> head;
    //Constructor oluşturmadık çünkü zaten headd null olarak oluşucak. Tekrar constructorda yazmaya gerek yok
    public Node<T> createNode(T val){  //Node sınıfından bir değer alıyor.
        return new Node<T>(val);
    }
    public void insertToFront(T val){  //Başa yani sola eklemek
        Node<T> newNode=createNode(val);
        newNode.next=head;  //Önce yeni node u head e bağla sonra onu yeni head yap
        head=newNode;
    }
    public void insertToEnd(T val){
        Node<T> newNode=createNode(val);
        if(head==null){
            head=newNode;
            return;  //Burada değer döndürmesine gerek yok sadece güncellemesi yeterli
            // o yüzden return ün yanında bir şey yok
        }
        Node<T> iterator=head;
        while(iterator.next!=null)
            iterator=iterator.next;
        iterator.next=newNode;

    }

    /*public boolean search(T val){
        Node<T> iterator=head;
        while (iterator!=null){
            if (iterator.value.compareTo(val)==0){
                return true;
            }
            iterator=iterator.next;
        }
        return false;
    } */
    public T findMin(){
        if (head==null)
            return null;
        T min=head.value;
        Node<T> iterator=head.next;
        while (iterator!=null){
            if(min.compareTo(iterator.value)==1)
                min=iterator.value;
            iterator=iterator.next;
        }
        return min;
    }
    public void sortedInsert(T val){
        Node<T> newNode=createNode(val);
        if(head==null)
            head=newNode;
        else if (val.compareTo(head.value)<=0){
            newNode.next=head;
            head=newNode;
        }else{
            Node<T> iterator=head;
            while(iterator.next!=null && iterator.next.value.compareTo(val)==-1){
                iterator=iterator.next;
            }
            newNode.next=iterator.next;
            iterator.next=newNode;
        }
    }

    public void deleteTheFirst(){
        if(head!=null)
            head=head.next;
    }

    public void deleteFromEnd(){
        if (head != null) {
            if(head.next==null){
                head=null;
                return;
            }
            Node<T> iterator, previous;
            iterator = previous = head;

            while (iterator.next != null) {
                previous = iterator;
                iterator = iterator.next;
            }
            previous.next = null;
        }
        else {
            System.out.println("List is empty");
        }
    }
    public void delete(T val){
        if(head==null)
            return;
        if(head.value.compareTo(val)==0) // girdiğimiz sayı headin değerine eşit değilse headi arttırır
            head=head.next;              // Generic class olduğu için direkt karşılaştıramayız
        else{                            // O yüzden compareTo
            Node<T> previous=head, iterator=head;
            while(iterator!=null && iterator.value.compareTo(val)!=0)   {
                previous=iterator;
                iterator=iterator.next;
            }
            if(iterator!=null)
                previous.next=iterator.next;

        }
    }

    public void recursiveAddToEnd(T val){

        head=recursiveAddToEnd(head, val);
    }
    public Node<T> recursiveAddToEnd(Node<T> tempHead, T val)
    {
        if(tempHead==null)
            return createNode(val);
        else{
            tempHead.next=recursiveAddToEnd(tempHead.next, val);
        }
        return tempHead;
    }

    public void display(){
        Node<T> iterator=head;
        while(iterator!=null){
            System.out.print(iterator+ " ");  //Node classında toString olduğu için iterator.value gerek yok
            iterator=iterator.next;
        }
        System.out.println();

    }

    //------------------------------------------------------------------------------------------------------------//

    static long a;
    static long b;
    public void displayFirst10(){
        Node<T> iterator=head;
        int counter = 0;
        while (iterator!=null){
            System.out.print(iterator+" ");
            iterator=iterator.next;
            counter++;
            if (counter==10) break;
        }
    }

    public void search(T val,LinkedList<T> linkedlist1) throws FileNotFoundException{  //search txtsi için. Sadece işlem sayısı lazım
        long startTime1 = System.nanoTime();
        int accessCount=0;
        double averageCount;
        Node<T> iterator=linkedlist1.head;
        while (iterator!=null && iterator.value.compareTo(val)!=0){
            accessCount++;
            iterator=iterator.next;
        }
        averageCount=accessCount/10000.0;
        if(iterator.value.compareTo(val)==0){
            accessCount++;
            System.out.println(val+" was found in the Search.txt");
            System.out.println("Total number of memory access is:"+accessCount);
            System.out.println("Average number of memory access is:"+averageCount);
        }else {
            System.out.println(val+" was not found in the Search.txt");
        }
        long endTime1 = System.nanoTime();
        long elapsedTime1 = endTime1 - startTime1;
        a=elapsedTime1;
        System.out.println("Total elapsed time is:"+elapsedTime1+" nano second. \n");
    }

    public void searchAndMoveToFront(T val,LinkedList<T> linkedlist1,LinkedList<T> linkedlistsearch) throws FileNotFoundException {  //Source
        long startTime2 = System.nanoTime();
        int accessCount=0;
        int valCounter=0;
        double averageCount;
        Node<T> iterator=linkedlist1.head;  //linkedListSource
        Node<T> iterator2=linkedlistsearch.head;  //linkedListSource
        while (iterator2!=null && iterator2.value.compareTo(val)!=0){
            accessCount++;
            iterator2=iterator2.next;
        }
        if(iterator2.value.compareTo(val)==0){
            accessCount++;
            System.out.println(val+" was found in the Search.txt");
        }else {
            System.out.println(val+" was not found in the Search.txt");
        }
        while (iterator!=null && iterator.value.compareTo(val)!=0){
            accessCount++;
            iterator=iterator.next;
            if(iterator.value.compareTo(val)==0){
                valCounter++;
            }
        }
        averageCount=accessCount/10000.0;
        if(iterator.value.compareTo(val)==0){
            if(iterator!=null){
                if(valCounter==1){
                    Node<T> newnode=createNode(val);
                    newnode.next=head;
                    head=newnode;
                    linkedlist1.displayFirst10();
                    System.out.println("\r");
                }
            }
        }
        long endTime2 = System.nanoTime();
        long elapsedTime2 = endTime2 - startTime2;
        b=elapsedTime2;
        System.out.println("Total number of memory access is:"+accessCount);
        System.out.println("Average number of memory access is:"+averageCount);
        System.out.println("Total elapsed time is:"+elapsedTime2+" nano second. \n");
    }

    public void compareMethods(){
        if (a < b) {
            System.out.println("Method Search is faster than Method searchAndMoveToFront.");
            System.out.println("Gap is:"+(b-a));
        } else if (a > b) {
            System.out.println("Method Search is slower than Method searchAndMoveToFront.");
            System.out.println("Gap is:"+(a-b)+" nano second.");
        } else {
            System.out.println("Speed of Method Search and Method searchAndMoveToFront are equal.");
        }
    }

}