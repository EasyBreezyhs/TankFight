package com.TankFight.Utils;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * <h3>test0628</h3>
 * <p>MapCreate</p>
 *
 * @author : EasyBreezyhs
 * @date : 2022-07-06 09:51
 **/
public class CreateMap {
    //�Թ���������
    //�Թ���������
    static int rows = 7;
    static int cols = 7;
    static int row = 2;
    static int col = 2;

    public static void printMaze(int[][][] maze) {
        /*
         *ÿ���Թ���Ԫռ3x5��С��x��ʾǽ��0��ʾ��ǽ�����ĵ�Ĭ��Ϊ0,�ĸ���Ĭ��Ϊǽ���ո�Ϊ�����ʽ��
         * */
        //�����Թ�
        int[][] battlefield = new int[rows * 3][cols * 3];
        for (int i = 0; i < rows; i++) {
            StringBuffer str1 = new StringBuffer();
            StringBuffer str2 = new StringBuffer();
            StringBuffer str3 = new StringBuffer();
            for (int j = 0; j < cols; j++) {
                //Ĭ�϶�Ϊǽ
                char[][] str = new char[][]{{'x', ' ', 'x', ' ', 'x'}, {'x', ' ', '0', ' ', 'x'}, {'x', ' ', 'x', ' ', 'x'}};
                if (maze[i][j][0] == 1) {
                    str[1][0] = '0';
                }
                if (maze[i][j][1] == 1) {
                    str[2][2] = '0';
                }
                if (maze[i][j][2] == 1) {
                    str[1][4] = '0';
                }
                if (maze[i][j][3] == 1) {
                    str[0][2] = '0';
                }
                //���浽str1/2/3��
                str1.append(str[0][0]).append(str[0][1]).append(str[0][2]).append(str[0][3]).append(str[0][4]).append(' ');
                str2.append(str[1][0]).append(str[1][1]).append(str[1][2]).append(str[1][3]).append(str[1][4]).append(' ');
                str3.append(str[2][0]).append(str[2][1]).append(str[2][2]).append(str[2][3]).append(str[2][4]).append(' ');
            }


            System.out.println(str1.toString());
            System.out.println(str2.toString());
            System.out.println(str3.toString());
        }
    }

    public static int[][] printMazeRemake(int[][][] maze) {
        /*
         *ÿ���Թ���Ԫռ3x5��С��x��ʾǽ��0��ʾ��ǽ�����ĵ�Ĭ��Ϊ0,�ĸ���Ĭ��Ϊǽ���ո�Ϊ�����ʽ��
         * */
        //�����Թ�
        int[][] battlefield = new int[rows * 3][cols * 3];
        int count = 0;
        String s1;
        for (int i = 0; i < rows; i++) {
            StringBuffer str1 = new StringBuffer();
            StringBuffer str2 = new StringBuffer();
            StringBuffer str3 = new StringBuffer();
            for (int j = 0; j < cols; j++) {
                //Ĭ�϶�Ϊǽ
                char[][] str = new char[][]{{'1', ' ', '1', ' ', '1'}, {'1', ' ', '0', ' ', '1'}, {'1', ' ', '1', ' ', '1'}};
                if (maze[i][j][0] == 1) {
                    str[1][0] = '0';
                }
                if (maze[i][j][1] == 1) {
                    str[2][2] = '0';
                }
                if (maze[i][j][2] == 1) {
                    str[1][4] = '0';
                }
                if (maze[i][j][3] == 1) {
                    str[0][2] = '0';
                }
                //���浽str1/2/3��
                str1.append(str[0][0]).append(str[0][1]).append(str[0][2]).append(str[0][3]).append(str[0][4]).append(' ');
                str2.append(str[1][0]).append(str[1][1]).append(str[1][2]).append(str[1][3]).append(str[1][4]).append(' ');
                str3.append(str[2][0]).append(str[2][1]).append(str[2][2]).append(str[2][3]).append(str[2][4]).append(' ');
            }

            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < str1.toString().replaceAll(" ", "").length(); k++) {
                    if (j == 0) {
                        battlefield[count][k] = Integer.parseInt(String.valueOf(str1.toString().replaceAll(" ", "").toCharArray()[k]));
                    } else if (j == 1) {
                        battlefield[count][k] = Integer.parseInt(String.valueOf(str2.toString().replaceAll(" ", "").toCharArray()[k]));
                    } else if (j == 2) {
                        battlefield[count][k] = Integer.parseInt(String.valueOf(str3.toString().replaceAll(" ", "").toCharArray()[k]));
                    }
                }
                count++;
            }
        }

        for (int[] ints : battlefield) {
            for (int anInt : ints) {
                System.out.print(anInt);
            }
            System.out.println();
        }
        return battlefield;
    }

    public static int[][][] randomPrimCreateM() {
        //����rows��cols��������ռ�,init
        int[][][] maze = new int[rows][cols][5];

        maze = new int[rows][cols][5];
        List<Integer> list = new LinkedList<Integer>();
        list.add(row);
        list.add(col);
        while (!list.isEmpty()) {
            /* 1.��list�����ѡ��һ��·�����������prim�㷨����˼�롣
             * 2.�������ѡȡ������·��Ȩֵ��С����ѡ��С���Ǹ����Ǿͱ�Ϊ��С��������prim�㷨˼�롣
             * */
            int random = new Random().nextInt((int) (list.size() / 2));
            //�����ѡȡ��·���Ƴ�list
            row = list.remove(2 * random);
            col = list.remove(2 * random);//�Ƴ���col�洢λ�û�ǰ��һλ
            maze[row][col][4] = 1;
            StringBuffer str = new StringBuffer();
            if (col > 0) {
                if (maze[row][col - 1][4] == 1) {
                    str.append('L');
                } else if (maze[row][col - 1][4] == 0) {
                    list.add(row);
                    list.add(col - 1);
                    maze[row][col - 1][4] = 2;
                }
            }
            if (row > 0) {
                if (maze[row - 1][col][4] == 1) {
                    str.append('U');
                } else if (maze[row - 1][col][4] == 0) {
                    list.add(row - 1);
                    list.add(col);
                    maze[row - 1][col][4] = 2;
                }
            }
            if (col < cols - 1) {
                if (maze[row][col + 1][4] == 1) {
                    str.append('R');
                } else if (maze[row][col + 1][4] == 0) {
                    list.add(row);
                    list.add(col + 1);
                    maze[row][col + 1][4] = 2;
                }
            }
            if (row < rows - 1) {
                if (maze[row + 1][col][4] == 1) {
                    str.append('D');
                } else if (maze[row + 1][col][4] == 0) {
                    list.add(row + 1);
                    list.add(col);
                    maze[row + 1][col][4] = 2;
                }
            }
            //ֻҪ�к�ѡ·���߾����ѡȡһ�����򲢴�ͨ�������Թ���Ԫ��·��
            if (str.length() != 0) {
                //����ѡ·����Ϊ2��������[0,2)���������ֵ
                int random2 = new Random().nextInt(str.length());
                //��ͨ�����Թ���Ԫ��·��
                if (str.charAt(random2) == 'L') {
                    maze[row][col][0] = 1;
                    maze[row][--col][2] = 1;
                }
                if (str.charAt(random2) == 'U') {
                    maze[row][col][3] = 1;
                    maze[--row][col][1] = 1;
                }
                if (str.charAt(random2) == 'R') {
                    maze[row][col][2] = 1;
                    maze[row][++col][0] = 1;
                }
                if (str.charAt(random2) == 'D') {
                    maze[row][col][1] = 1;
                    maze[++row][col][3] = 1;
                }
            }
        }
        return maze;
    }

    public static void main(String[] args) {
        //printMaze(randomPrimCreateM());
//        //printMazeRemake(randomPrimCreateM());
//        int[][] ints = Test.printMazeRemake(Test.randomPrimCreateM());
//        for (int[] in : ints) {
//            for (int ins : in) {
//                System.out.print(ins);
//            }
//            System.out.println();
//        }
    }
}