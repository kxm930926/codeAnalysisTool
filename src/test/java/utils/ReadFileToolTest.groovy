package utils

import VO.Text
import spock.lang.Specification
import spock.lang.Unroll

class ReadFileToolTest extends Specification {
    @Unroll("#path")
    def "TextResult"() {
        when:
       File file =  getFileByPath(path)
        if(file ==null){
            return
        }
        Text text = ReadFileTool.textResult(file,word)
        then:
         count == text.existNum
        where:
        count  | path                                    | word
        1      | "C:\\Users\\24943\\Desktop\\Demo.java"  |"getPrimarySheet"
        null   | "C:\\Users\\24943\\Desktop\\Demo.jav"  |"getPrimarySheet"
    }

    private static File getFileByPath(String path){
        File file = new File(path)
        if (!file.exists()) {
            return null
        } else if (!file.isFile()) {
            return null
        } else {
            return file
        }
    }
}
