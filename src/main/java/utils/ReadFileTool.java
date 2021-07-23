package utils;

import VO.Text;

import java.io.*;
import java.util.ArrayList;

/**
 *
 */

public class ReadFileTool {

    /**
     * @param file 文件绝对路径
     * @return 文本分析结果
     * @throws IOException io错误
     */
    private static Text textResult(File file) throws IOException {
        boolean isComment = false;
        int whiteLines = 0;
        int commentLines = 0;
        int normalLines = 0;
        String line = "";
        int lineCount = 0;
        BufferedReader br = new BufferedReader(new FileReader(file));
        while ((line = br.readLine()) != null) {
            lineCount++;
            line = line.trim();
            if (line.matches("^[//s&&[^//n]]*$")) {
                // 空行
                whiteLines++;
            } else if (line.startsWith("/*") && !line.endsWith("*/")) {
                // 为"/*"开头的注释行
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
                .existLines(new ArrayList<Integer>())
                .build();
        br.close();
        return result;
    }
}