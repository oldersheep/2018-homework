package com.xxx.jvm;

/**
 * @Description class文件结构解析 javap -verbose ClassFile.class
 * @Date 2019/7/3 21:05
 * @Version 1.0
 */
public class ClassFile {

    public static void main(String[] args) {
        int a = 2;
        int b = 40;
        int c = a + b;
        System.out.println(c);
    }

    /*
    E:\AHomeWork\2018-homework\08-performance-optimize\03-jvm\target\classes\com\xxx\jvm>javap -verbose ClassFile.class
Classfile /E:/AHomeWork/2018-homework/08-performance-optimize/03-jvm/target/classes/com/xxx/jvm/ClassFile.class
  Last modified 2019-7-3; size 586 bytes
  MD5 checksum bea76e2a3e73119733fcf0d47530d8d5
  Compiled from "ClassFile.java"
public class com.xxx.jvm.ClassFile
  minor version: 0
  major version: 52
  flags: ACC_PUBLIC, ACC_SUPER
Constant pool:
   #1 = Methodref          #5.#23         // java/lang/Object."<init>":()V
   #2 = Fieldref           #24.#25        // java/lang/System.out:Ljava/io/PrintStream;
   #3 = Methodref          #26.#27        // java/io/PrintStream.println:(I)V
   #4 = Class              #28            // com/xxx/jvm/ClassFile
   #5 = Class              #29            // java/lang/Object
   #6 = Utf8               <init>
   #7 = Utf8               ()V
   #8 = Utf8               Code
   #9 = Utf8               LineNumberTable
  #10 = Utf8               LocalVariableTable
  #11 = Utf8               this
  #12 = Utf8               Lcom/xxx/jvm/ClassFile;
  #13 = Utf8               main
  #14 = Utf8               ([Ljava/lang/String;)V
  #15 = Utf8               args
  #16 = Utf8               [Ljava/lang/String;
  #17 = Utf8               a
  #18 = Utf8               I
  #19 = Utf8               b
  #20 = Utf8               c
  #21 = Utf8               SourceFile
  #22 = Utf8               ClassFile.java
  #23 = NameAndType        #6:#7          // "<init>":()V
  #24 = Class              #30            // java/lang/System
  #25 = NameAndType        #31:#32        // out:Ljava/io/PrintStream;
  #26 = Class              #33            // java/io/PrintStream
  #27 = NameAndType        #34:#35        // println:(I)V
  #28 = Utf8               com/xxx/jvm/ClassFile
  #29 = Utf8               java/lang/Object
  #30 = Utf8               java/lang/System
  #31 = Utf8               out
  #32 = Utf8               Ljava/io/PrintStream;
  #33 = Utf8               java/io/PrintStream
  #34 = Utf8               println
  #35 = Utf8               (I)V
{
  public com.xxx.jvm.ClassFile();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=1, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: return
      LineNumberTable:
        line 8: 0
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       5     0  this   Lcom/xxx/jvm/ClassFile;

  public static void main(java.lang.String[]);
    descriptor: ([Ljava/lang/String;)V
    flags: ACC_PUBLIC, ACC_STATIC # 访问类型与方式
    Code:
      stack=2, locals=4, args_size=1
         0: iconst_2 # 常量2压栈
         1: istore_1 # 出栈保存到本地变量1里面
         2: bipush        40
         4: istore_2
         5: iload_1
         6: iload_2
         7: iadd
         8: istore_3
         9: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
        12: iload_3
        13: invokevirtual #3                  // Method java/io/PrintStream.println:(I)V
        16: return
      LineNumberTable:
        line 11: 0
        line 12: 2
        line 13: 5
        line 14: 9
        line 15: 16
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0      17     0  args   [Ljava/lang/String;
            2      15     1     a   I
            5      12     2     b   I
            9       8     3     c   I
}
SourceFile: "ClassFile.java"
     */
}
