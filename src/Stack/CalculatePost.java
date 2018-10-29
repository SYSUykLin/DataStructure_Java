package Stack;


import java.util.HashMap;
import java.util.Map;

public class CalculatePost {
    private String str;

    private Map<Character, Integer> map = new HashMap<>();

    public CalculatePost(String str) {
        this.str = str;
        map.put('+', 0);
        map.put('-', 0);
        map.put('*', 1);
        map.put('/', 1);
    }

    public String midToPost() {
        String postString = "";
        Stack stack = new Stack(str.length() + 1);
        char[] charString = str.toCharArray();
        for (int i = 0; i < charString.length; i++) {
            char c = charString[i];
            if (c >= '0' && c <= '9') {
                postString += c;
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                char pop = (char) stack.pop();
                while (pop != '(') {
                    postString += pop;
                    pop = (char) stack.pop();
                }
            } else {
                if (!stack.isEmpty()){
                    char pop = (char) stack.pop();
                    if (pop == '('){
                        stack.push(pop);
                        stack.push(c);
                        continue;
                    }
                    if (map.get(pop) > map.get(c)){
                        postString += pop;
                        stack.push(c);
                    }else {
                        stack.push(pop);
                        stack.push(c);
                    }
                }else {
                    stack.push(c);
                }
            }
        }

        while (!stack.isEmpty()){
            char pop = (char) stack.pop();
            postString += pop;
        }

        return postString;
    }

    public int getValue(String str1) {
        char[] charString = str1.toCharArray();
        Stack stack = new Stack(charString.length + 1);
        for (int i = 0; i < charString.length; i++) {
            char c = charString[i];
            if (c >= '0' && c <= '9') {
                stack.push((int) (c - '0'));
            } else {
                int num2 = stack.pop(), num1 = stack.pop(), temp = 0;
                if (c == '+') {
                    temp = num2 + num1;
                } else if (c == '-') {
                    temp = num1 - num2;
                } else if (c == '*') {
                    temp = num1 * num2;
                } else if (c == '/') {
                    temp = num1 / num2;
                }
                stack.push(temp);
            }
        }

        return stack.pop();
    }

     public static void main(String args[]){
        String str = "((3+2)*(6+7))*5";
        CalculatePost calculatePost = new CalculatePost(str);
        System.out.println(calculatePost.midToPost());
        System.out.println(calculatePost.getValue(calculatePost.midToPost()));
     }
}
