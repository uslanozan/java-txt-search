public class Node<T> {
    public T value;
    public Node<T> next;

    public Node(T val){
        this.value=val;
        this.next=null;
    }
    public String toString(){
        return String.valueOf(value);  //String.valueOf(val) başka tip bir şeyi string yapmak için kullaanılır
        //Aynısının Integer ve Double hali vb. mevcuttur
    }
}