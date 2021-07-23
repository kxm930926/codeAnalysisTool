package VO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
public class Text {
    /**
     * 有效程序行数
     **/
    private int normalLines ;
    /**
     * 有效程序行数
     **/
    private int whiteLines ;
    /**
     * 注释行数
     **/
    private int commentLines ;
    /**
     * 总行数
     */
    private int totalLines ;
    /**
     * 字符存在的行数
     */
    private List<Integer> existLines;
}
