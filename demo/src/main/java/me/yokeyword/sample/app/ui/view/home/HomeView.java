package me.yokeyword.sample.app.ui.view.home;

import me.yokeyword.sample.app.ui.fragment.home.HomeFragment;
import me.yokeyword.sample.app.ui.view.BaseViewInterface;

/**
 * Created by xu on 2017/6/17.
 */

public interface HomeView extends BaseViewInterface {
    HomeFragment getContent();
}
