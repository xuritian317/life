package me.yokeyword.sample.app.ui.view.data;

import me.yokeyword.sample.app.ui.fragment.data.child.DataContentFragment;
import me.yokeyword.sample.app.ui.view.BaseViewInterface;

/**
 * Created by xu on 2017/6/18.
 */

public interface DataContentView extends BaseViewInterface {
    DataContentFragment getContent();

    void setTotal(int total);

    int getTotal();

    void setIsTop(boolean flag);

    boolean isTop();

    void scrollToTop();

    void refresh();
}
