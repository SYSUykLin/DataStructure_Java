package Stack;

public class Stack {
    private int[] datas;
    private int tapIndex = -1;
    private int lenght = 0;

    public Stack(int lenght) {
        this.lenght = lenght;
        datas = new int[this.lenght];
    }

    public void push(int data) {
        datas[++tapIndex] = data;
    }

    public int pop() {
        return datas[tapIndex--];
    }

    public int peek() {
        return datas[tapIndex];
    }

    public boolean isEmpty() {
        return tapIndex == -1;
    }

    public boolean isFull() {
        return tapIndex == (lenght - 1);
    }

    public static boolean checkBrackets(String string) {
        char[] charString = string.toCharArray();
        Stack stack = new Stack(10);
        for (int i = 0; i < charString.length; i++) {
            char c = charString[i];
            if (c == '{' || c == '[' || c == '(') {
                stack.push(c);
            } else if (c == '}' || c == ']' || c == ')') {
                char popElement = (char) stack.pop();
                if ((popElement == '{' && c != '}')
                        || (popElement == '[' && c != ']')
                        || (popElement == '(' && c != ')')
                ) {
                    return false;
                } else {
                    continue;
                }
            } else {
                continue;
            }
        }
        if (stack.isEmpty())
            return true;
        else {
            return false;
        }
    }

    public static void main(String args[]) {
        String str = "{[1*2] +[2*3] + }";
        System.out.println(Stack.checkBrackets(str));
    }

}
