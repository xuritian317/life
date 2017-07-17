package me.yokeyword.sample.app.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.fragmentation.anim.FragmentAnimator;
import me.yokeyword.fragmentation.helper.FragmentLifecycleCallbacks;
import me.yokeyword.sample.R;
import me.yokeyword.sample.app.base.BaseActivity;
import me.yokeyword.sample.app.base.BaseMainFragment;
import me.yokeyword.sample.app.ui.event.TabSelectedEvent;
import me.yokeyword.sample.app.ui.fragment.community.child.CommContentFragment;
import me.yokeyword.sample.app.ui.fragment.data.child.DataContentFragment;
import me.yokeyword.sample.app.ui.fragment.home.HomeFragment;
import me.yokeyword.sample.app.ui.fragment.home.child.HomeContentFragment;
import me.yokeyword.sample.app.ui.fragment.my.MyFragment;
import me.yokeyword.sample.app.ui.fragment.my.child.MeFragment;
import me.yokeyword.sample.app.ui.fragment.data.DataFragment;
import me.yokeyword.sample.app.ui.fragment.community.CommunityFragment;
import me.yokeyword.sample.app.ui.customer.BottomBar;
import me.yokeyword.sample.app.ui.customer.BottomBarTab;

/**
 * 类知乎 复杂嵌套Demo tip: 多使用右上角的"查看栈视图"
 * Created by YoKeyword on 16/6/2.
 */
public class MainActivity extends SupportActivity implements BaseMainFragment.OnBackToFirstListener {
    public static final int home = 0;
    public static final int data = 1;
    public static final int community = 2;
    public static final int my = 3;

    private SupportFragment[] mFragments = new SupportFragment[4];

    private BottomBar mBottomBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        EventBus.getDefault().register(this);

        if (savedInstanceState == null) {
            mFragments[home] = HomeFragment.newInstance();
            mFragments[data] = DataFragment.newInstance();
            mFragments[community] = CommunityFragment.newInstance();
            mFragments[my] = MyFragment.newInstance();

            loadMultipleRootFragment(R.id.fl_container, home,
                    mFragments[home],
                    mFragments[data],
                    mFragments[community],
                    mFragments[my]);
        } else {
            // 这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题

            // 这里我们需要拿到mFragments的引用,也可以通过getSupportFragmentManager.getFragments()自行进行判断查找(效率更高些),用下面的方法查找更方便些
            mFragments[home] = findFragment(HomeFragment.class);
            mFragments[data] = findFragment(DataFragment.class);
            mFragments[community] = findFragment(CommunityFragment.class);
            mFragments[my] = findFragment(MyFragment.class);
        }

        initView();


        // 可以监听该Activity下的所有Fragment的18个 生命周期方法
        registerFragmentLifecycleCallbacks(new FragmentLifecycleCallbacks() {

            @Override
            public void onFragmentSupportVisible(SupportFragment fragment) {
                Log.i("MainActivity", "onFragmentSupportVisible--->" + fragment.getClass().getSimpleName());
            }
        });
    }

    @Override
    protected FragmentAnimator onCreateFragmentAnimator() {
        return super.onCreateFragmentAnimator();
    }

    private void initView() {
        mBottomBar = (BottomBar) findViewById(R.id.bottomBar);

        mBottomBar.addItem(new BottomBarTab(this, R.drawable.home))
                .addItem(new BottomBarTab(this, R.drawable.data))
                .addItem(new BottomBarTab(this, R.drawable.ic_message_white_24dp))
                .addItem(new BottomBarTab(this, R.drawable.ic_account_circle_white_24dp));

        mBottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, int prePosition) {
                showHideFragment(mFragments[position], mFragments[prePosition]);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {
                SupportFragment currentFragment = mFragments[position];
                int count = currentFragment.getChildFragmentManager().getBackStackEntryCount();

                // 如果不在该类别Fragment的主页,则回到主页;
                if (count > 1) {
                    if (currentFragment instanceof HomeFragment) {
                        currentFragment.popToChild(HomeContentFragment.class, false);
                    } else if (currentFragment instanceof DataFragment) {
                        currentFragment.popToChild(DataContentFragment.class, false);
                    } else if (currentFragment instanceof CommunityFragment) {
                        currentFragment.popToChild(CommContentFragment.class, false);
                    } else if (currentFragment instanceof MyFragment) {
                        currentFragment.popToChild(MeFragment.class, false);
                    }
                    return;
                }

                // 这里推荐使用EventBus来实现 -> 解耦
                if (count == 1) {
                    // 在FirstPagerFragment中接收, 因为是嵌套的孙子Fragment 所以用EventBus比较方便
                    // 主要为了交互: 重选tab 如果列表不在顶部则移动到顶部,如果已经在顶部,则刷新
                    EventBus.getDefault().post(new TabSelectedEvent(position));
                }
            }
        });
    }

    @Override
    public void onBackPressedSupport() {
        // 对于 4个类别的主Fragment内的回退back逻辑,已经在其onBackPressedSupport里各自处理了
        super.onBackPressedSupport();
    }

    @Override
    public void onBackToFirstFragment() {
        mBottomBar.setCurrentItem(0);
    }

    /**
     * 这里暂没实现,忽略
     */
//    @Subscribe
//    public void onHiddenBottombarEvent(boolean hidden) {
//        if (hidden) {
//            mBottomBar.hide();
//        } else {
//            mBottomBar.show();
//        }
//    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
//        EventBus.getDefault().unregister(this);
    }
}
