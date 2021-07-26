import VO.Text;
import utils.ReadFileTool;

import java.io.File;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            System.out.println("请输入一个文件夹路径");
            Scanner sc = new Scanner(System.in);
            String pathStr = sc.nextLine();
            System.out.println("请输入待搜索字段");
            String word = sc.nextLine();
            File file = new File(pathStr);
            if (!file.exists()) {
                System.out.println("你输入的文件路径不存在,请重新输入");
            } else if (file.isFile()) {
                Text text = ReadFileTool.textResult(file, word);
                System.out.println(file.getName() +
                        " ,有效行数" + text.getNormalLines() +
                        " ,空白行数" + text.getWhiteLines() +
                        " ,注释行数" + text.getCommentLines() +
                        " ,总行数" + text.getTotalLines());
                if (!ReadFileTool.isBlack(word)) {
                    if (text.getExistNum() > 0) {
                        System.out.println(" ,待搜索字段共出现" + text.getExistNum() +
                                " 次,出现的行数为" + text.getExistLines());
                    } else {
                        System.out.println("待搜索字段不存在");
                    }
                }
            } else {
                System.out.println("你输入的是文件路径,请重新输入");
            }
        } catch (Exception e) {
            System.out.println("读取表达式异常，请重新输入");
        }
    }
}
