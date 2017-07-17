package me.yokeyword.sample.app.ui.view.community;

import java.util.List;

import me.yokeyword.sample.app.model.entity.ShareInfo;
import me.yokeyword.sample.app.ui.view.BaseViewInterface;

/**
 * Created by xu on 2017/6/20.
 */

public interface CommContentView extends BaseViewInterface {
    void setAdapterData(List<ShareInfo> infoList);

    void setRefresh(boolean flag);

    boolean isTop();

    void refresh();

    void scrollToTop();

    int getTotal();

    void setTotal(int total);

    void setIsTop(boolean b);
}
