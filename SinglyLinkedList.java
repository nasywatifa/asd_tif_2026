import java.util.Objects;

/**
 * SinglyLinkedList
 */
public class SinglyLinkedList implements LinkedList{

    public Node head,tail;
    private int size=0;

    public SinglyLinkedList(){
        head=tail=null;
    }
    public boolean isEmpty(){
        return(size==0);
    }
    public int size(){
        return size;
    }
    public void addFirst(Object inputData){
        Node baru = new Node(inputData);
        if (isEmpty()){
            head = baru;
            tail = baru;
            size++;
        }
        else {
            baru.pointer = head;
            head = baru;
            size++;
        }
    }

    public void addLast(Object inputData){
        Node baru = new Node(inputData);
        if (isEmpty()){
            head = baru;
            tail = baru;
            size++;
        }
        else {
            tail.pointer = baru;
            tail = baru;
            size++;
        }
    }

    public void addAfter(int index,Object inputData){
        if(isEmpty()){
            addFirst(inputData);
            return;
        }
        if(index<0 || index>=size){
            System.err.println("Index tidak valid");
            return;
        }
        Node baru = new Node(inputData);
        Node C=head;
        for(int i=0;i<index;i++){
            C = C.pointer;
        }
        baru.pointer = C.pointer;
        C.pointer = baru;
        if(C==tail){
            tail = baru;
        }
        size++;
    }

    public void deleteFirst(){
        if(isEmpty()){
            System.err.println("Linked list kosong");
        }
        else if(size==1){//else if(head==tail)
            head = null;
            tail = null;
            size--;
        }
        else{
            head = head.pointer;
            size--;
        }
    }

    public void deleteLast(){
        if(isEmpty()){
            System.err.println("Linked list kosong");
        }
        else if(size==1){//else if(head==tail)
            head = null;
            tail = null;
            size--;
        }
        else{
            Node temp = head;
            for(int i=1;i<size-1;i++){
                temp = temp.pointer;
            }
            tail = temp;
            tail.pointer = null;
            size--;
        }
    }

    public void deleteAfter(int index){
        if(isEmpty()){
            System.err.println("Linked list kosong");
        }
        else if(index<0 || index>=size-1){
            System.err.println("Index tidak valid");
        }
        else{
            Node temp = head;
            for(int i=0;i<index;i++){
                temp = temp.pointer;
            }
            if(temp.pointer==tail){
                tail = temp;
            }
            temp.pointer = temp.pointer.pointer;
            size--;
        }
    }

    public void print(){
        Node currentNode = head;
        for(int i =0;i<size;i++){
            System.out.println(currentNode.data);
            currentNode = currentNode.pointer;
        }
    }
    @Override
    public Object get(int index) {
        // TODO digunakan untuk mengembalikan data pada index ke-i dimulai dari head. Head memiliki index 0
        if(index<0 || index>=size){
            throw new IndexOutOfBoundsException("Index: "+index+", Size: "+size);
        }
        Node currentNode = head;
        for(int i=0;i<index;i++){
            currentNode = currentNode.pointer;
        }
        return currentNode.data;
    }
    @Override
    public int indexOf(Object targetData) {
        // TODO digunakan mencari kemunculan pertama targetData pada linked list dan mengembalikan indeksnya. Indeks dari head adalah 0. Jika tidak ada targetData pada linked list, kembalikan nilai -1 
        Node currentNode = head;
        for(int i=0;i<size;i++){
            if(Objects.equals(currentNode.data, targetData)){
                return i;
            }
            currentNode = currentNode.pointer;
        }
        return -1;
    }
    @Override
    public void printReverse() {
        // TODO digunakan untuk mencetak data pada linked list dengan urutan terbalik, dari tail ke head.
        Object[] data = toArray();
        for(int i=data.length-1;i>=0;i--){
            System.out.println(data[i]);
        }
    }
    @Override
    public boolean remove(Object targetData) {
        // TODO digunakan untuk mencari dan menghapus node dengan data=targetData pada linked list serta mengembalikan nilai True jika berhasil, dan False jika targetData tidak ada di linkedList
        if(isEmpty()){
            return false;
        }
        if(Objects.equals(head.data, targetData)){
            deleteFirst();
            return true;
        }
        Node prevNode = head;
        Node currentNode = head.pointer;
        while(currentNode!=null){
            if(Objects.equals(currentNode.data, targetData)){
                prevNode.pointer = currentNode.pointer;
                if(currentNode==tail){
                    tail = prevNode;
                }
                size--;
                return true;
            }
            prevNode = currentNode;
            currentNode = currentNode.pointer;
        }
        return false;
    }
    @Override
    public Object[] toArray() {
        // TODO digunakan untuk mendapatkan keseluruhan data pada node-node di linked list dalam bentuk array. Data-data pada array disusun secara urut mulai dari head sampai dengan tail.
        Object[] result = new Object[size];
        Node currentNode = head;
        for(int i=0;i<size;i++){
            result[i] = currentNode.data;
            currentNode = currentNode.pointer;
        }
        return result;
    }
}