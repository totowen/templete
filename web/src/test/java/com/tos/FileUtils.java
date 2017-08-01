package com.tos;


import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by qq136 on 2017/8/1.
 */
public class FileUtils {


    public static void main(String[] args) {
//        traverseFolder1("E:\\Users\\alienware\\IdeaProjects\\untitled");
//          traverseFolder2("E:\\Users\\alienware\\IdeaProjects\\untitled");
        List<File> fileList = getFileList("E:\\Users\\alienware\\IdeaProjects\\untitled");
        fileList.stream()
                .forEach(s-> System.out.println(s.getName()));
    }

    public static List<File> getFileList(String path){

        List<File> fileList = new ArrayList<>();
        File file = new File(path);
        File[] files = file.listFiles();
        if(null!=files){
            for (int i = 0;i<files.length;i++){
                String name = files[i].getName();
                if(files[i].isDirectory()){
                    getFileList(files[i].getAbsolutePath());
                }else if (name.endsWith(".xml")){
                    String absolutePath = files[i].getAbsolutePath();
                    System.out.println("文件名： "+absolutePath);
                    fileList.add(files[i]);
                }else{
                    continue;
                }
            }
        }
        return fileList;
    }

    /**
     * 使用递归的方法调用
     */
    public static void  traverseFolder2(String path){
        File file = new File(path);
        if(file.exists()){
            File[] files = file.listFiles();
            if (files.length==0){
                System.out.println("文件夹为空！");
                return;
            }else{
                for (File file2:files
                     ) {
                    if(file2.isDirectory()){
                        System.out.println("文件夹："+file2.getAbsolutePath());
                        traverseFolder2(file2.getAbsolutePath());
                    }else {
                        System.out.println("文件： "+file2.getAbsolutePath());
                    }
                }
            }
        }else{
            System.out.println("文件不存在");
        }
    }

    /**
     * 不使用递归的方法调用
     * @param path
     */
    public static void traverseFolder1(String path){
        int fileNum = 0,folderNum = 0;
        File file = new File(path);
        if(file.exists()){//检测文件是否存在
            LinkedList<File> list = new LinkedList<File>();
            File[] files = file.listFiles();
            for (File file2:
                 files) {
                if(file2.isDirectory()){
                    System.out.println("文件夹： "+file2.getAbsolutePath());
                    list.add(file2);
                    folderNum++;
                }else{
                    System.out.println("文件: "+file2.getAbsolutePath());
                    fileNum++;
                }
            }
            File temp_file;
            while (!list.isEmpty()){
                temp_file = list.removeFirst();//获取第一个文件夹
                files = temp_file.listFiles();
                for (File file2:files
                     ) {
                    if(file2.isDirectory()){
                        System.out.println("文件夹： "+file2.getAbsolutePath());
                        list.add(file2);
                        folderNum++;
                    }else{
                        System.out.println("文件: "+file2.getAbsolutePath());
                        fileNum++;
                    }
                }
            }
        }else{
            System.out.println("文件不存在!");
        }
        System.out.println("文件夹拥有："+folderNum+"， 文件共有："+fileNum);
    }

}
