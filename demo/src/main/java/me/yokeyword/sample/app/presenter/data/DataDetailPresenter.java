package me.yokeyword.sample.app.presenter.data;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobRealTimeData;
import cn.bmob.v3.exception.BmobException;
import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.sample.R;
import me.yokeyword.sample.app.db.DBManager;
import me.yokeyword.sample.app.model.dao.BmobQueryUserData;
import me.yokeyword.sample.app.model.dao.BmobSetUserData;
import me.yokeyword.sample.app.model.dao.BmobUpdateUserData;
import me.yokeyword.sample.app.model.entity.DataInfo;
import me.yokeyword.sample.app.model.entity.DetailInfo;
import me.yokeyword.sample.app.model.entity.DetailInfoDao;
import me.yokeyword.sample.app.model.entity.UserData;
import me.yokeyword.sample.app.presenter.BasePresenter;
import me.yokeyword.sample.app.ui.view.data.DataDetailView;

/**
 * Created by xu on 2017/6/19.
 */

public class DataDetailPresenter extends BasePresenter {
    private DataDetailView view;

    public DataDetailPresenter(DataDetailView view) {
        this.view = view;
    }

    private DetailInfoDao dao = DBManager.getInstance().detailInfoDao;

    BmobRealTimeData data;

    public void getData(final String type) {
        view.setRefreshLayout(true);
        List<DetailInfo> lists = dao.queryBuilder().where(DetailInfoDao.Properties.Type.eq(type)).list();
        if (!lists.isEmpty()) {
            view.setAdapterData(lists);
            view.setRefreshLayout(false);
            return;
        }
        BmobQueryUserData.getInstance(new BmobQueryUserData.QueryDataCallBack() {
            @Override
            public void successCallBack(final String userTel, final String userName, List<UserData> infoList) {
                final List<DetailInfo> detailInfos = new ArrayList<DetailInfo>();
                if (!infoList.isEmpty()) {
                    for (UserData data : infoList) {
                        List<DataInfo> dataInfos = data.getData();
                        for (DataInfo info : dataInfos) {
                            detailInfos.add(new DetailInfo(data.getObjectId(), data.getUserName(), data.getUserTel(), data.getType(), info.getDate(), info.getValue()));
                        }
                    }
                    view.setAdapterData(detailInfos);
                    dao.insertInTx(detailInfos);
                    view.setRefreshLayout(false);
                    return;
                }

                final List<DataInfo> dataInfos = new ArrayList<DataInfo>();
                for (int i = 1; i <= 30; i++) {
                    int value = (int) (Math.random() * 10000);
                    dataInfos.add(new DataInfo("1月" + i + "日", value));
                }

                UserData data = new UserData(userTel, userName, type, dataInfos);
                BmobSetUserData.getInstance(new BmobSetUserData.SetDataCallBack() {
                    @Override
                    public void successCallBack(String s) {
                        for (DataInfo info : dataInfos) {
                            detailInfos.add(new DetailInfo(s, userTel, userName, type, info.getDate(), info.getValue()));
                        }
                        view.setAdapterData(detailInfos);
                        dao.insertInTx(detailInfos);
                        view.setRefreshLayout(false);
                    }
                }).inputUser(data);
            }

            @Override
            public void failCallBack(String userTel, String userName, BmobException e) {

            }

        }).getUserData(type);

    }

    public void showDialog(SupportActivity mActivity, final DetailInfo info, final int position) {
        //弹出对话框
        View dialogView = mActivity.getLayoutInflater().inflate(R.layout.item_dialog, null);
        TextView tvData = (TextView) dialogView.findViewById(R.id.tv_data);
        final EditText edtValue = (EditText) dialogView.findViewById(R.id.edt_value);

        tvData.setText(info.getData());

        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity)
                .setIcon(R.drawable.life)
                .setView(dialogView)
                .setTitle("请输入数据");


        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String inputStr = edtValue.getText().toString();

                if (TextUtils.isEmpty(inputStr)) {
                    view.showToast("输入不能为空");
                    return;
                }
                info.setValues(Integer.parseInt(inputStr));
                view.changeData(position, info);
                dao.update(info);
                BmobUpdateUserData.getInstance().updateUserDataValue(info.getObjectId(), position, info.getValues());

            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.setCancelable(true);
        builder.show();
    }

    public void showCalendarView(SupportActivity mActivity, final List<DetailInfo> infoList, final int position) {
        //弹出对话框
        View dialogView = mActivity.getLayoutInflater().inflate(R.layout.item_calendar, null);
        final TextView tvMouth = (TextView) dialogView.findViewById(R.id.tv_mouth);
        TextView tvDay = (TextView) dialogView.findViewById(R.id.tv_day);

        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity)
                .setIcon(R.drawable.life)
                .setView(dialogView)
                .setTitle("请输入日期");

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String inputMouth = tvMouth.getText().toString();
                String inputDay = tvMouth.getText().toString();

                if (TextUtils.isEmpty(inputMouth) && TextUtils.isEmpty(inputDay)) {
                    view.showToast("输入不能为空");
                    return;
                }
                int mouth = Integer.parseInt(inputMouth);
                int day = Integer.parseInt(inputDay);
                int start = position;
                for (int i = start; i < 30; i++) {
                    DetailInfo info = infoList.get(i);
                    info.setData(mouth + "月" + day + "日");
                    dao.update(info);
                    view.changeData(i, info);

                    infoList.set(i, info);

                    day++;
                    if (mouth == 1 || mouth == 3 || mouth == 5 || mouth == 7 || mouth == 9 || mouth == 11) {
                        if (day > 31) {
                            mouth++;
                            day = 1;
                        }
                    } else if (mouth == 2) {
                        if (day > 28) {
                            mouth++;
                            day = 1;
                        }
                    } else {
                        if (day > 30) {
                            mouth++;
                            day = 1;
                        }
                    }
                    if (mouth > 12)
                        mouth = 1;
                }
                BmobUpdateUserData.getInstance().updateUserDate(infoList.get(0).getObjectId(), infoList);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.setCancelable(true);
        builder.show();
    }
}
