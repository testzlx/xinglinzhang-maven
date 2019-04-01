package com.sankuai;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class HufmanCodec {
    final float DEFAULT = 10f;

    class Node {
        Node(char ch, float weight) {
            this.ch = ch;
            this.weight = weight;
        }

        Node() {
        }

        Node(float weight) {
            this.weight = weight;
        }

        float weight;
        char ch;
        int lchild, rchild, parent;
    }

    //哈夫曼树构建完成后，各个元素01组成的结果集合
    List<CodeType> codeTypes = new ArrayList<CodeType>();

    private void setCodeTypes(Node[] resultNodes, Node[] initNodes) {
        for (int i = 0; i < initNodes.length; i++) {
            int c = i;
            Node node = initNodes[i];
            CodeType code = new CodeType(node.ch, initNodes.length);
            int parent = node.parent;
            while (parent != 0) {
                code.start--;
                if (resultNodes[parent].lchild == c) {
                    code.bits[code.start] = '0';
                } else {
                    code.bits[code.start] = '1';
                }
                c = parent;
                parent = resultNodes[parent].parent;
            }
            codeTypes.add(code);
        }
    }

    public List<CodeType> getCodeTypes() {
        return this.codeTypes;
    }

    class CodeType {
        int start;
        char ch;
        char bits[];

        CodeType(char ch, int start) {
            this.ch = ch;
            this.start = start;
            this.bits = new char[start];
        }

        CodeType() {

        }

    }

    public void compose(Node[] resultNodes, Node[] initNodes) {
        final int size = initNodes.length;
        for (int i = 0; i < resultNodes.length; i++) {
            if (i < size) {
                resultNodes[i] = initNodes[i];
            } else {
                resultNodes[i] = new Node(DEFAULT);
            }
        }
        for (int i = size; i < resultNodes.length; i++) {
            int indexMin1 = 0, indexMin2 = 0; //indexMin1 ：最小节点下标，indexMin2：次小节点下标
            float value1, value2;  //value1：最小值，value2：次小值
            value1 = value2 = DEFAULT;
            for (int j = 0; j < resultNodes.length; j++) {
                if (resultNodes[j].weight < value1 && resultNodes[j].parent == 0) {
                    value2 = value1;
                    value1 = resultNodes[j].weight;
                    indexMin2 = indexMin1;
                    indexMin1 = j;
                } else if (resultNodes[j].weight < value2 && resultNodes[j].parent == 0) {
                    value2 = resultNodes[j].weight;
                    indexMin2 = j;
                }
            }
            resultNodes[i].lchild = indexMin1;
            resultNodes[i].rchild = indexMin2;
            resultNodes[i].weight = add(resultNodes[indexMin1].weight, resultNodes[indexMin2].weight);
            resultNodes[indexMin1].parent = i;
            resultNodes[indexMin2].parent = i;
        }
        setCodeTypes(resultNodes, initNodes);
    }


    private Node[] init() {
        Node[] nodes = new Node[6];
        nodes[0] = new Node('A', 0.4f);
        nodes[1] = new Node('B', 0.3f);
        nodes[2] = new Node('C', 0.1f);
        nodes[3] = new Node('D', 0.1f);
        nodes[4] = new Node('E', 0.02f);
        nodes[5] = new Node('F', 0.08f);
        return nodes;
    }

    public List<Character> encode(String s) {
        List<Character> characters = new ArrayList();
        List<CodeType> codeTypes = getCodeTypes();
        char[] chars = s.toCharArray();
        for (char ch : chars) {
            for (CodeType codeType : codeTypes) {
                if (codeType.ch == ch) {
                    for (int i = codeType.start; i < codeType.bits.length; i++) {
                        characters.add(codeType.bits[i]);
                    }
                }
            }

        }
        return characters;
    }

    public List<Character> decode(List<Character> characters, Node[] hufmanTree) {
        List<Character> result = new ArrayList<Character>();
        int m = hufmanTree.length - 1;
        for (char ch : characters) {
            if (ch == '0') {
                m = hufmanTree[m].lchild;
            } else {
                m = hufmanTree[m].rchild;
            }
            if (hufmanTree[m].lchild == 0) {
                result.add(hufmanTree[m].ch);
                m = hufmanTree.length - 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        //initialValueTest();
        HufmanCodec hufmanCodec = new HufmanCodec();
        Node[] result = new Node[11];
        hufmanCodec.compose(result, hufmanCodec.init());
        List<Character> characters = hufmanCodec.encode("ABBCEA");
        System.out.println("characters:" + characters);
        List<Character> origin = hufmanCodec.decode(characters, result);
        System.out.println("origin:" + origin);
    }

    static void initialValueTest() {
        HufmanCodec hufmanCodec = new HufmanCodec();
        HufmanCodec.Node node = hufmanCodec.new Node();
        System.out.println("weight:" + node.weight + " char:" + node.ch + " lchild:" + node.lchild);
        float a = 0.2f, b = 0.8f;
        float sum = add(a, b);
        System.out.println("sum:" + sum);
    }

    public static float add(float v1, float v2) {
        BigDecimal b1 = new BigDecimal(Float.toString(v1));
        BigDecimal b2 = new BigDecimal(Float.toString(v2));
        return b1.add(b2).floatValue();
    }
}




