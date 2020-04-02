package md2html;


import java.io.*;
import java.util.Stack;

public class Md2Html {
    static String fin;
    static String fout;
    static BufferedWriter writer;
    private static Stack<StringBuilder> strWordsStack = new Stack<>();
    private static Stack<StringBuilder> strWordsStack2 = new Stack<>();
    private static Stack<StringBuilder> strMarksStack = new Stack<>();
    private static Stack<StringBuilder> strMarksStack2 = new Stack<>();

    public static void main(String[] args) throws IOException {
        fin = args[0];
        fout = args[1];
        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fout), "utf8"));
        Writer writer2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output1.txt"), "utf8"));
        Scanner scanner = new Scanner(new File(fin), "utf8");
        StringBuilder strBuild = new StringBuilder();
        while (scanner.hasNextLine()) {
            StringBuilder stringBuilder = new StringBuilder(scanner.nextLine());
            while (stringBuilder.length() > 0) {
                strBuild.append(stringBuilder.append("\n"));
                stringBuilder = new StringBuilder(scanner.nextLine());
            }
            if (strBuild.length() > 0) {
                if (strBuild.charAt(strBuild.length() - 1) == '\n') {
                    strBuild.deleteCharAt(strBuild.length() - 1);
                }
                StringBuilder endStringBuilder = new StringBuilder();
                for (int i = 0; i < strBuild.length(); i++) {
                    switch (strBuild.charAt(i)) {
                        case '\\':
                            break;
                        case '<':
                            endStringBuilder.append("&lt;");
                            break;
                        case '>':
                            endStringBuilder.append("&gt;");
                            break;
                        case '&':
                            endStringBuilder.append("&amp;");
                            break;
                        default:
                            endStringBuilder.append(strBuild.charAt(i));
                    }
                }
                getType(endStringBuilder.toString());
                strBuild = new StringBuilder();
            }
        }
        scanner.close();
        writer.close();
    }

    private static void parse(String str) throws IOException {
        strWordsStack = new Stack<>();
        strMarksStack = new Stack<>();
        Scanner scanner = new Scanner(str);
        StringBuilder strBefore = new StringBuilder();
        StringBuilder strMark = new StringBuilder();
        StringBuilder strNewMark = new StringBuilder();
        while (scanner.hasNextLine()) {
            strBefore = scanner.getStringBeforeMark();
            strMark = scanner.nextMark();
            strWordsStack.push(strBefore);
            while (strWordsStack.size() > strMarksStack.size() + 1) {
                StringBuilder s1 = strWordsStack.pop();
                StringBuilder s2 = strWordsStack.pop();
                strWordsStack.push(s2.append(s1));
            }
            if (!strMarksStack.empty() && strMarksStack.peek().toString().contentEquals(strMark)) {
                strWordsStack.push(getNewWordWithBorder(strMark.toString(), strWordsStack.pop().toString()));
                strMarksStack.pop();
            } else {
                strMarksStack.push(strMark);
            }
        }
        strMarksStack.pop();
        while (!strWordsStack.empty()) {
            strWordsStack2.push(strWordsStack.pop());
        }
        while (!strMarksStack.empty()) {
            strMarksStack2.push(strMarksStack.pop());
        }
        while (!strWordsStack2.empty()) {
            writer.write(strWordsStack2.pop().toString());
            if (!strMarksStack2.empty()) {
                writer.write(strMarksStack2.pop().toString());
            }
        }
        writer.write('\n');
        scanner.close();
    }

    private static StringBuilder getNewWordWithBorder(String border, String word) {
        StringBuilder strTmp = new StringBuilder();
        switch (border) {
            case "**":
            case "__":
                strTmp = new StringBuilder("<strong>").append(word).append("</strong>");
                return strTmp;
            case "*":
            case "_":
                strTmp = new StringBuilder("<em>").append(word).append("</em>");
                return strTmp;
            case "--":
                strTmp = new StringBuilder("<s>").append(word).append("</s>");
                return strTmp;
            case "`":
                strTmp = new StringBuilder("<code>").append(word).append("</code>");
                return strTmp;
            case "++":
                strTmp = new StringBuilder("<u>").append(word).append("</u>");
                return strTmp;
        }
        return strTmp;
    }

    private static void getType(String string) throws IOException {
        String tmpStr;
        boolean isOctothorp = string.charAt(0) == '#';
        int countOctothorp = 0;
        int position = 0;
        while (string.charAt(position) == '#') {
            position++;
        }
        boolean isEndOctothorp = Character.isWhitespace(string.charAt(position));
        countOctothorp += position;
        if (isOctothorp && isEndOctothorp) {
            tmpStr = "<h" + countOctothorp + ">" + string.substring(countOctothorp + 1) + "</h" + countOctothorp + ">";
        } else {
            tmpStr = "<p>" + string + "</p>";
        }
        parse(tmpStr);
    }
}