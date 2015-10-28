package cn.com.cienet.lenovoyxt.network;


/**
 * 网络响应接口
 *
 * @param <T>
 */
public interface NetWorkRespListener<T> {
    /**
     * 网络响应成功
     *
     * @param response
     */
    public void onSuccessResponse(T response);

    /**
     * 网络响应成功
     *
     * @param response
     * @param type     响应类型
     */
    public void onSuccessResponse(T response, String type);

    /**
     * 网络响应失败
     *
     * @param error
     */
    public void onErrorResponse(String error);

}
