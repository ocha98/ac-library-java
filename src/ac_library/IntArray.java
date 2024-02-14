package ac_library;

public final class IntArray {
    private int size = 0;
    private int capacity = 0;
    private int[] data;
    
    public IntArray(int size) {
        if(size <= 0)throw new RuntimeException("size must be greater than 0");
        this.size = size;
        capacity = size;
        data = new int[capacity];
    }

    public IntArray() {
        size = 0;
        capacity = 1;
        data = new int[capacity];
    }

    public int size() {
        return this.size;
    }

    public void reserve(int new_capacity) {
        capacity = new_capacity;
        int[] newData = new int[capacity];
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
    }

    public void push(int v) {
        if(capacity == size){
            reserve(capacity << 1);
        }
        data[size++] = v;
    }

    public int pop() {
        if(isEmpty()) throw new IndexOutOfBoundsException("Stack is empty.");
        return data[--size];
    }

    public int get(int i) {
        if(i < 0 || i >= size) throw new IndexOutOfBoundsException(String.format("index %d is out of range. Array size is %d.", i, size));
        return data[i];
    }
    
    public void set(int i, int v) {
        if(i < 0 || i >= size) throw new IndexOutOfBoundsException(String.format("index %d is out of range. Array size is %d.", i, size));
        data[i] = v;
    }
    
    public int back() {
        if(isEmpty()) throw new IndexOutOfBoundsException("Stack is empty.");
        return data[size - 1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public IntArray copy() {
        IntArray array = new IntArray();
        array.size = this.size;
        array.capacity = this.capacity;
        array.data = data.clone();
        
        return array;
    }
}
