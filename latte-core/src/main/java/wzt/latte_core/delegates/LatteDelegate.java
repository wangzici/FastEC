package wzt.latte_core.delegates;

/**
 * @author Tao
 * @date 2018/2/26
 * desc:
 */
public abstract class LatteDelegate extends PermissionCheckerDelegate {
    @SuppressWarnings("unchecked")
    public <T extends LatteDelegate> T getParentDelegate() {
        return (T) getParentFragment();
    }
}
