package com.tos;

import java.io.*;

/**
 * Created by qq136 on 2017/7/28.
 */
public class CodeCounter {

    static long normalLines = 0;
    static long commentLines = 0;
    static long whiteLines = 0;

    public static void main(String[] args) {

        File file = new File("E:\\Users\\alienware\\IdeaProjects\\untitled\\src\\com\\company\\");
        File[] files = file.listFiles();
        for (File child : files){
            if(child.getName().matches(".*\\.java$")){
                parse(child);
            }
        }

        System.out.println("NormalLines :" +normalLines);
        System.out.println("CommentLines :" +commentLines);
        System.out.println("WhiteLines :" +whiteLines);

    }

    private static void parse(File file) {
        BufferedReader bufferedReader = null;
        boolean comment = false;
        try {
            bufferedReader =  new BufferedReader(new FileReader(file));
            String line = "";
            while((line = bufferedReader.readLine())!=null){
                if(line.matches("^[\\s&&[^\\n]]*$")){ //空的并且不是反斜杠n
                    whiteLines ++;
                }else if (line.startsWith("/*") && !line.endsWith("*/")){  //TODO 只有在"/*"顶格的情况下才行，否则不能判定为注释的开始位置
                    commentLines ++;
                    comment = true;
                }else if(comment){
                    commentLines ++;
                    if(line.endsWith("*/")){
                        comment = false;
                    }
                }else {
                    normalLines ++;
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(bufferedReader!=null){
                try {
                    bufferedReader.close();
                    bufferedReader = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
