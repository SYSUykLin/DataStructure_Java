package HuffmanTree;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HuffmanTree {

    public void compress(String str, String fileName) {
        HuffmanPriorityQueue huffmanPriorityQueue = this.statistics(str);
        HuffmanNode huffmanTree = buildHuffmanTree(huffmanPriorityQueue);
        Map<String, String> huffmanCode = new HashMap<>();
        buildHuffmanCode(huffmanCode, huffmanTree, "");
        System.out.println(huffmanCode);
        this.outData(str, huffmanCode, fileName);
    }

    private void outData(String str, Map<String, String> map, String outName) {
        File outFile = new File(outName);
        DataOutputStream dataOutputStream = null;
        try {
            dataOutputStream = new DataOutputStream(new FileOutputStream(outFile));
            outCode(dataOutputStream, map);
            String dataHuffmanCode = source2Code(str, map);
            outDataHuffmanCode(dataOutputStream, dataHuffmanCode);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                dataOutputStream.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private void outDataHuffmanCode(DataOutputStream os, String datacode) throws IOException {
        byte [] bs = string2ByteArray(datacode);
        os.writeInt(bs.length);
        os.write(bs);
    }

    private byte [] string2ByteArray(String dataHuffmanCode){
        byte [] bytes = null;
        char [] cs = dataHuffmanCode.toCharArray();
        int len = cs.length;
        int lenbytes = 0;
        if (len % 8 == 0){
            lenbytes = len/8 + 1;
            bytes = new byte[lenbytes];
            for (int i = 0; i < lenbytes - 1; i++) {
                String s = "";
                for (int j = i*8; j < (i+1)*8; j++) {
                    s += cs[j];
                }
                bytes[i] = char2bytes(s);
            }
            bytes[lenbytes-1] = 0;
        }
        return null;
    }

    private byte char2bytes(String s){
        byte ret = 0;
        char[] cs = s.toCharArray();

        for(int i=0;i<cs.length;i++){
            byte tempB = (byte)(Byte.parseByte(""+cs[i])*Math.pow(2,cs.length-i-1));
            ret = (byte)(ret+tempB);
        }
        return ret;

    }

    private String source2Code(String string, Map<String, String> map){
        StringBuffer stringBuffer = new StringBuffer();
        char [] cs = string.toCharArray();
        for (char c:cs) {
            stringBuffer.append(map.get(""+c));
        }
        return stringBuffer.toString();
    }

    private void outCode(DataOutputStream os, Map<String, String> code) throws IOException {
        os.writeInt(code.size());
        for (String key:code.keySet()) {
            os.writeChar(key.charAt(0));
            os.writeInt(code.get(key).length());
            os.writeChars(code.get(key));
        }
    }

    private void buildHuffmanCode(Map<String, String> map, HuffmanNode huffmanTree, String stringCode) {
        if (huffmanTree.getLeftNode() == null && huffmanTree.getRightNode() == null){
            map.put(""+huffmanTree.getC(), stringCode);
            return;
        }if(huffmanTree.getLeftNode() != null){
            buildHuffmanCode(map, huffmanTree.getLeftNode(), stringCode + "0");
        }if (huffmanTree.getRightNode() != null){
            buildHuffmanCode(map, huffmanTree.getRightNode(), stringCode + "1");
        }
    }

    private HuffmanNode buildHuffmanTree(HuffmanPriorityQueue huffmanPriorityQueue) {
        while (huffmanPriorityQueue.size() > 1) {
            HuffmanNode left = huffmanPriorityQueue.remove();
            HuffmanNode right = huffmanPriorityQueue.remove();
            HuffmanNode root = new HuffmanNode((char) 0, left.getCount() + right.getCount());
            root.setLeftNode(left);
            root.setRightNode(right);
            huffmanPriorityQueue.insert(root);
        }
        return huffmanPriorityQueue.peekFront();
    }

    private HuffmanPriorityQueue statistics(String str) {
        Map<Character, Integer> map = new HashMap<>();
        char[] charString = str.toCharArray();
        for (char c : charString) {
            Object object = map.get(c);
            if (object == null) {
                map.put(c, 1);
            } else {
                map.put(c, ((Integer) object) + 1);
            }
        }
        HuffmanPriorityQueue huffmanPriorityQueue = new HuffmanPriorityQueue(map.size());
        for (char c : map.keySet()) {
            HuffmanNode node = new HuffmanNode(c, map.get(c));
            huffmanPriorityQueue.insert(node);
        }
        return huffmanPriorityQueue;
    }

     public static void main(String args[]){
        HuffmanTree huffmanTree = new HuffmanTree();
        //huffmanTree.compress("aaabbcdddd");
     }
}
