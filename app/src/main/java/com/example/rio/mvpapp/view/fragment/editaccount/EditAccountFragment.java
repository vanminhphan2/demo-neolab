package com.example.rio.mvpapp.view.fragment.editaccount;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.rio.mvpapp.R;
import com.example.rio.mvpapp.model.User;
import com.example.rio.mvpapp.presenter.main.setting.EditAccountPresenter;
import com.example.rio.mvpapp.view.fragment.setting.SettingFragment;
import com.rengwuxian.materialedittext.MaterialEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import es.dmoral.toasty.Toasty;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditAccountFragment extends Fragment implements EditAccountViewListener {


    @BindView(R.id.img_user)
    ImageView imgUser;
    @BindView(R.id.edt_user_name)
    MaterialEditText edtUserName;
    @BindView(R.id.edt_gender)
    MaterialEditText edtGender;
    @BindView(R.id.btn)
    Button btn;
    Unbinder unbinder;

    private User user;
    private EditAccountPresenter editAccountPresenter;
    private Context context;

    public EditAccountFragment() {
        // Required empty public constructor
    }

    public static EditAccountFragment newInstance(User user) {
        EditAccountFragment editAccountFragment = new EditAccountFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("INFO_USER", user);
        editAccountFragment.setArguments(bundle);
        return editAccountFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_account, container, false);
        unbinder = ButterKnife.bind(this, view);
        context = getContext();
        editAccountPresenter = new EditAccountPresenter(this, context);

        if (getArguments() != null) {

            user = (User) getArguments().getSerializable("INFO_USER");
            if (user != null) {
                Log.e("Rio ", "user - - user--> " + user.toString());
                Glide.with(this)
                        .load(R.drawable.rio)
                        .apply(RequestOptions.circleCropTransform())
                        .into(imgUser);
                edtUserName.setText(user.getName());
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = String.valueOf(edtUserName.getText());
                        user.setName(name);
                        editAccountPresenter.save(user);
                    }
                });
            }
        }
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void saveInfoSuccess(User user) {

        Toasty.success(context, "Save Success!", Toast.LENGTH_SHORT, true).show();
        assert getParentFragment() != null;
        SettingFragment settingFragment = (SettingFragment) getParentFragment();
        settingFragment.setData(user);
        settingFragment.getChildFragmentManager().popBackStack();

    }

    @Override
    public void saveInfFail() {
        Toasty.warning(context, "Save that bai", Toast.LENGTH_SHORT, true).show();
    }
}
