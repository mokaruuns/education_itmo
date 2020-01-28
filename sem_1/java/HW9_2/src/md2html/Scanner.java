package md2html;

import java.io.*;

class Scanner {

    private int read = 0;
    private Reader reader = null;
    private boolean isEof = false;
    private int nowInt = 0;
    private StringBuilder nowMark = new StringBuilder();
    private int position = 0;
    private char[] block = null;


    Scanner(File file, String charset) throws IOException {
        reader = new InputStreamReader(new FileInputStream(file), charset);
        getBlock();
    }

    Scanner(String s) throws IOException {
        reader = new InputStreamReader(new ByteArrayInputStream(s.getBytes()));
        getBlock();
    }


    boolean hasNextLine() {
        return !isEof;
    }

    String nextLine() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        while (block[position] != '\n' && hasNextLine()) {
            stringBuilder.append(block[position]);
            moveIndex();
        }
        moveIndex();
        while (stringBuilder.length()>0 && (stringBuilder.charAt(stringBuilder.length()-1)=='\n' || stringBuilder.charAt(stringBuilder.length()-1)=='\r')){
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
        }
        return stringBuilder.toString();
    }

//    String getParagraph() throws IOException {
//
//            switch (block[position]) {
//                case '\\':
//                    break;
//                case '<':
//                    stringBuilder.append("&lt;");
//                    break;
//                case '>':
//                    stringBuilder.append("&gt;");
//                    break;
//                case '&':
//                    stringBuilder.append("&amp;");
//                    break;
//                default:
//                    stringBuilder.append(block[position]);
//            }
//            moveIndex();
//        }
//        return stringBuilder.toString();
//    }

    StringBuilder nextMark() {
        return nowMark;
    }

    StringBuilder getStringBeforeMark() throws IOException {
        nowMark = new StringBuilder();
        StringBuilder stringBuilder = new StringBuilder();
        while (!isEof && (block[position] != '+' && block[position] != '*' && block[position] != '_' && block[position] != '-' && block[position] != '`')) {
            stringBuilder.append(block[position]);
            moveIndex();
        }
        nowMark.append(block[position]);
        moveIndex();
        if (!isEof && (block[position] == '+' || block[position] == '*' || block[position] == '_' || block[position] == '-' || block[position] == '`')) {
            if (block[position] == nowMark.charAt(0)) {
                nowMark.append(block[position]);
            }
            moveIndex();
        }

        return stringBuilder;
    }

    private void moveIndex() throws IOException {
        position++;
        if (position >= read) {
            position = 0;
            getBlock();
            if (read == -1) {
                isEof = true;
            }
        }
    }

    private void getBlock() throws IOException {
        block = new char[256];
        read = reader.read(block);
    }

    void close() throws IOException {
        block = new char[256];
        isEof = false;
        read = 0;
        position = 0;
        reader.close();
        reader = null;
    }

}
