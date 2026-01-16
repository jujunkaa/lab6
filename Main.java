
class Stack<T> {
    private T[] data;
    private int size;

    public Stack(int capacity) {
        data = (T[]) new Object[capacity];
        size = 0;
    }

    public void push (T element) {
        if (size == data.length) {
            throw new RuntimeException ("Стек переполнен!");
        }
        data[size] = element;
        size++;
    }

    public T pop() {
        if (size == 0) {
            throw new RuntimeException("Стек пустой!");
        }
        
        T element = data[size-1];
        data[size - 1] = null;
        size--;
        return element;
    }

    public T peek() {
        if (size == 0) {
            throw new RuntimeException("Стек пустой!");
        }
        return data[size-1];
    }
}


public class Main {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<> (10);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.pop());
        System.out.println(stack.peek());
        stack.push(4);
        System.out.println(stack.pop());
    }
}