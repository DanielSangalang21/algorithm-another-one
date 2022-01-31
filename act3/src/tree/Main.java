package tree;

import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static PriorityQueue<Element> huffmanCodes = new PriorityQueue<>();
    static PriorityQueue<Tree<Element>> huffmanTrees = new PriorityQueue<>();
    static char[] characters;

    public static void main(String[] args) {
        int choice;
        String message = "Welcome!\n\n"+
        "1. Generate Huffman Table\n"+
        "2. Convert Text to Huffman Code\n"+
        "3. Convert Huffman Code to Text\n"+
        "3. Convert Huffman Code to Text\n"+
        "4. Exit\n";
        do {
           System.out.println();
           System.out.println(message);

            choice = chooseFrom(4);

			/*
			 * switch (choice) { case 1 -> huffmanTable(frequencyTable()); case 2 ->
			 * textToHuffman(); case 3 -> huffmanToText(); case 4 -> System.exit(0); }
			 */
            
            switch(choice) {
            case 1 :
            	huffmanTable(frequencyTable());
              break;
            case 2:
            	textToHuffman();
              break;
            case 3:
            	huffmanToText();
            	break;
            case 4:
            System.exit(0);
            	break;
          }
        } while (true);
    }

    static void huffmanTable(PriorityQueue<Element> Elements) {
        do {
            Tree<Element> huffmanTree = new Tree<>();
            Element character = Elements.dequeue();
            huffmanTree.insertNode(character);
            huffmanCodes.enqueue(character);
            huffmanTrees.enqueue(huffmanTree);
        } while (!Elements.isEmpty());

        do {
            Tree<Element> previous = huffmanTrees.dequeue();
            Tree<Element> next = huffmanTrees.dequeue();
            Element sum = new Element(previous.getRoot().data.getFrequency() + next.getRoot().data.getFrequency());
            Tree<Element> huffmanTree = new Tree<>(sum, previous, next);
            huffmanTrees.enqueue(huffmanTree);
        } while (huffmanTrees.getList().getSize() > 1);

        generateCodesForAll(huffmanCodes);
        percentageSavings(huffmanCodes);
    }

    static void generateCodesForAll(PriorityQueue<Element> characters) {
        PriorityQueue<Element> temp = new PriorityQueue<>();
        int size = characters.getList().getSize() - 1;
        while (size >= 0) {
            Element character = huffmanCodes.dequeue();
            generateCode(huffmanTrees.firsElement().getRoot(), character, "", 0);
            temp.enqueue(character);
            size--;
        }

        while (!temp.isEmpty()) {
            characters.enqueue(temp.dequeue());
        }

        System.out.println("Character  Frequency   Code       Bits");
        System.out.println(huffmanCodes);
    }

    static void generateCode(TreeNode<Element> node, Element character, String code, int bits) {
        if (node.leftNode == null && node.rightNode == null && node.data.equals(character)) {
            character.setCode(code);
            character.setBits(bits);
            bits = 0;
        }

        if (node.leftNode != null) {
            generateCode(node.leftNode, character, code + "0", bits + 1);
        }

        if (node.rightNode != null) {
            generateCode(node.rightNode, character, code + "1", bits + 1);
        }
    }

    static PriorityQueue<Element> frequencyTable() {
        huffmanCodes = new PriorityQueue<>();
        huffmanTrees = new PriorityQueue<>();
        PriorityQueue<Character> unfilteredChars = new PriorityQueue<>();
        PriorityQueue<Element> Elements = new PriorityQueue<>();

        System.out.print("Input a sentence/phrase/paragraph: ");
        String str = sc.nextLine();
        System.out.println();

        characters = str.toCharArray();


        for (char c : characters) {
            unfilteredChars.enqueue(c); // HAHAHAH BECOMES AAAHHHH
        }

        while (!unfilteredChars.isEmpty()) {
            char symbol = unfilteredChars.dequeue();
            int frequency = 1;

            if (unfilteredChars.isEmpty()) {
                Elements.enqueue(new Element(symbol, frequency));
                break;
            }

            char next = unfilteredChars.dequeue();

            while (!unfilteredChars.isEmpty() && symbol == next) { // DROP CHARACTERS AFTER EACH COMPARISON.
                frequency++;
                next = unfilteredChars.dequeue();
            }

            if (symbol != next) { // INPUT: A A B C. SYMBOL: A. NEXT: B. BRING BACK B AFTER IT'S REMOVED BY DEQUEUE.
                unfilteredChars.enqueue(next);
            }

            if (unfilteredChars.isEmpty()) { // FIX FOR: SYMBOL = NEXT BUT QUEUE IS EMPTY SO FREQUENCY IS OFF BY 1.
                if (symbol == next) {
                    frequency++;
                }
            }
            Elements.enqueue(new Element(symbol,  frequency));
        }
        return Elements;
    }

    static void huffmanToText() {
        String str;
        String decoded = "";

        do {
            System.out.print("Input a huffman code: ");
            str = sc.nextLine();
            if (!str.matches("[0-1]+")) {
                System.out.println("Huffman code can only contain 1s and 0s with no spaces.");
            }
        } while (!str.matches("[0-1]+"));

        char[] huffman = str.toCharArray();
        String code = "";

        for (char digit : huffman) {
            code += digit;
            Element matchingCharacter = huffmanTrees.firsElement().find(huffmanTrees.firsElement().getRoot(), new Element(code));
            if (matchingCharacter != null) {
                decoded += matchingCharacter.getData();
                code = "";
            }
        }

        if (!decoded.isEmpty()) {
            System.out.println("Decode Text: " + decoded);
        }
    }
    static void textToHuffman() {
        String encoded = "";

        System.out.print("Input text: ");
        String str = sc.nextLine();
        char[] text = str.toCharArray();

        for (char character : text)
        {
            Element matchingCharacter = huffmanTrees.firsElement().find(huffmanTrees.firsElement().getRoot(), new Element(character));
            if (matchingCharacter != null) {
                encoded += matchingCharacter.getCode();
            } else {
                System.out.println("Text can only contain character/s in the input text that is/are part of the Huffman Table.");
                encoded = "";
                break;
            }
        }

        if (!encoded.isEmpty()) {
            System.out.println("Encoded Text: " + encoded);

        }
    }

    static int chooseFrom(int listSize) {
        Scanner sc = new Scanner(System.in);
        String choice;
        System.out.print("Choice: ");
        choice = sc.nextLine();
        while (!choice.matches("[1-" + listSize + "]")) {
            System.out.println("Enter a number from 1 to " + listSize + ".");
            choice = String.valueOf(chooseFrom(listSize));
        }
        System.out.println();
        return Integer.parseInt(choice);
    }

    static void percentageSavings(PriorityQueue<Element> characters) {
        PriorityQueue<Element> temp = new PriorityQueue<>();
        int size = characters.getList().getSize() - 1;
        int ascii = 0;
        int huffman = 0;
        while (size >= 0) {
            Element character = huffmanCodes.dequeue();
            ascii += character.getFrequency() * 7;
            huffman += character.getFrequency() * character.getBits();
            temp.enqueue(character);
            size--;
        }
        while (!temp.isEmpty()) {
            characters.enqueue(temp.dequeue());
        }

        float savings = (float) (ascii - huffman) / (ascii) * 100;

        System.out.println("\nPercentage of Storage Savings: " + savings + " %\n");
    }
}
