package utils;

import VO.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 代码文件解析工具
 */
public class ReadFileTool {

    /**
     * @param file 文件绝对路径
     * @return 文本分析结果
     * @throws IOException io错误
     */
    public static Text textResult(File file,String word) throws IOException {
        boolean isComment = false;
        int whiteLines = 0;
        int commentLines = 0;
        int normalLines = 0;
        String line;
        int lineCount = 0;
        List<Integer> existLines = new ArrayList<Integer>();
        int existNum = 0;
        BufferedReader br = new BufferedReader(new FileReader(file));
        while ((line = br.readLine()) != null) {
            lineCount++;
            line = line.trim();
            if (!isBlack(word) && line.contains(word)) {
                existNum = existNum + countWordTimes(line, word);
                existLines.add(lineCount);
            }
            if (line.matches("^[//s&&[^//n]]*$")) {
                // 空行
                whiteLines++;
            } else if (line.startsWith("/*") && !line.endsWith("*/")) {
                // 为"/*"开头的注释行
                commentLines++;
                isComment = true;
            } else if (isComment && !line.endsWith("*/")) {
                // 为多行注释中的一行（中间行）
                commentLines++;
            } else if (isComment && line.endsWith("*/")) {
                // 为多行注释的结束行
                commentLines++;
                isComment = false;
            } else if (line.startsWith("//")) {
                // 单行注释行
                commentLines++;
            } else {
                // 正常代码行
                normalLines++;
            }
        }
        Text result = Text.builder()
                .commentLines(commentLines)
                .normalLines(normalLines)
                .whiteLines(whiteLines)
                .totalLines(lineCount)
                .existLines(existLines)
                .existNum(existNum)
                .build();
        br.close();
        return result;
    }
    /**
     * 截取字符串统计字符串出现次数
     * @param text 长字符串
     * @param word 待搜索字符串
     * @return
     */
    private static int countWordTimes(String text, String word) {
        int times = 0;
        if (!isBlack(text) && !isBlack(word)) {
            String subText = text;
            int index;
            int wordLength = word.length();
            while (subText.length() >= wordLength && (index = subText.indexOf(word)) > -1) {
                subText = subText.substring(index + wordLength);
                times++;
            }
        }
        return times;
    }
    /**
     * 字符串是否为空
     * @param str 待统计字符
     * @return
     */
    public static boolean isBlack(String str) {
        return str == null || str.length() == 0;
    }
}