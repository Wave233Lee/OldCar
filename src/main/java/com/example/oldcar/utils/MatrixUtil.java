package com.example.oldcar.utils;

import java.io.*;

public class MatrixUtil {
    /**
     * 读取矩阵txt文件
     */
    public static Double[][] readMatrixFile(String path, int index){
        Double[][] array = new Double[index][index];
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(new File(path)));
            String tempString = null;
            int row =0;
            // 一次读入一行，直到读入null为文件结束
            while (!(tempString = reader.readLine()).isEmpty()) {
                String[] temp = tempString.split("\t");
                for(int i=0; i<temp.length; i++){
                    array[row][i] = Double.parseDouble(temp[i]);
                }
                row++;
            }
            reader.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return array;
    }

    /**
     * 写出矩阵txt文件
     */
    public static void writeMatrixFile(String path, Double[][] array, int index){
        try {
            FileWriter fw = new FileWriter(path);
            PrintWriter out = new PrintWriter(fw);
            //将数组中的数据写入到文件中，每行各数据之间TAB间隔
            for(int i=0; i<index; i++){
                for(int j=0; j<index; j++){
                    out.write(array[i][j]+"\t");
                }
                out.write("\r\n");
            }
            out.println();
            fw.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
