package me.yokeyword.sample.app.ui.view.home;


import me.yokeyword.sample.app.ui.fragment.home.child.HomeContentFragment;
import me.yokeyword.sample.app.ui.view.BaseViewInterface;

/**
 * Created by xu on 2017/6/17.
 */

public interface HomeContentView extends BaseViewInterface {
    HomeContentFragment getContent();

    void setIsTop(Boolean flag);

    boolean isTop();

    void setTotal(int value);

    void setRefreshLayout();

    void refresh();

    int getTotal();

    void fBtnHide();

    void fBtnShow();

    void scrollToTop();


}
