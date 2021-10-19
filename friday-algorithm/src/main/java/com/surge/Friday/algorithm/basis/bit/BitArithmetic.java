package com.surge.Friday.algorithm.basis.bit;

/**
 * <p>位运算：与、或、非、亦或
 *
 * @author Matt Liu
 * @version 1.0 created by 2020.05.14
 */
public class BitArithmetic {
    public static void main(String[] args) {
        int a0 = 0, b0 = 0;
        int a1 =1, b1 = 1;
        System.out.println("-------与运算&------");
        System.out.println("a0 & b0："+(a0 & b0));
        System.out.println("a1 & b1："+(a1 & b1));
        System.out.println("a0 & b1："+(a0 & b1));
        System.out.println("a1 & b0："+(a1 & b0));
        System.out.println("-------与运算&------");

        System.out.println("-------或运算|------");
        System.out.println("a0 | b0："+(a0 | b0));
        System.out.println("a1 | b1："+(a1 | b1));
        System.out.println("a0 | b1："+(a0 | b1));
        System.out.println("a1 | b0："+(a1 | b0));
        System.out.println("-------或运算|------");

        System.out.println("-------非运算~------");
        System.out.println("~a0 ："+~a0);
        System.out.println("a0 = 0 = 00000000 00000000 00000000 00000000 -> 11111111 11111111 11111111 11111111");
        System.out.println("第一个1是符号位，1代表负数。1 1111111 11111111 11111111 11111111");
        System.out.println("负数以补码的形式存在：11111111 11111111 11111111 11111111 -> 10000000 00000000 00000000 00000001取反+1");
        System.out.println("所以10000000 00000000 00000000 00000001 = -1");
        System.out.println("~a1 ："+~a1);
        System.out.println("~b0 ："+~b0);
        System.out.println("~b1 ："+~b1);
        System.out.println("-------非运算~------");

        System.out.println("-------亦或运算^------");
        System.out.println("a0 ^ b0："+(a0 ^ b0));
        System.out.println("a1 ^ b1："+(a1 ^ b1));
        System.out.println("a0 ^ b1："+(a0 ^ b1));
        System.out.println("a1 ^ b0："+(a1 ^ b0));
        System.out.println("亦或运算也称为\"不进位加法\",2个bit相加不进位。");
        System.out.println("0 ^ x = x ");
        System.out.println("1 ^ x = ~x");
        System.out.println("所以2次亦或必能还原。和0亦或结果是本身，一次就可以。和1亦或是取反，需要两次。");

        System.out.println("快速交换a0和b1：");
        a0 = a0 ^ b1;
        b1 = a0 ^ b1;
        a0 = a0 ^ b1;
        System.out.println("a0="+a0+"b1="+b1);
        System.out.println("-------亦或运算^------");


    }
}
