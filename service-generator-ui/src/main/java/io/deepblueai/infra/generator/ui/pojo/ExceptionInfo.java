package io.deepblueai.infra.generator.ui.pojo;

/**
 * @author lutiehua
 * @date 2018-12-5
 */
public interface ExceptionInfo {

    /**
     * 得到错误编码
     *
     * @return
     */
    int getCode();

    /**
     * 得到错误信息
     *
     * @return
     */
    String getMessage();

}
