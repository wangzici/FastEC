package wzt.latte_core.util.callback;

/**
 * @author Tao
 * @date 2018/3/29
 * desc:
 */
public enum CallbackType {
    /**
     * desc:裁剪图片后的回调
     */
    ON_CROP,
    /**
     * desc:打开推送开关的回调
     */
    TAG_OPEN_PUSH,
    /**
     * desc:关闭推送开关的回调
     */
    TAG_STOP_PUSH,
    /**
     * desc:扫描到二维码数据后的回调
     */
    ON_SCAN;
}
