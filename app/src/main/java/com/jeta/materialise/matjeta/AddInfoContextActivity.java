package com.jeta.materialise.matjeta;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.jeta.materialise.presenter.AddInfoContextPresenter;

/**
 * Created by ArifHBtz on 5/29/2015.
 */
public class AddInfoContextActivity extends AppCompatActivity {

    private Button mBtnAddContext;
    private EditText mEtInfoContext;

    private AddInfoContextPresenter mPresenter;

    public AddInfoContextActivity(){
        mPresenter = new AddInfoContextPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_info_context);

        mBtnAddContext = (Button)findViewById(R.id.btn_use_context);
        mEtInfoContext = (EditText)findViewById(R.id.et_info_context);

        mPresenter.handleBtnUseContext(mBtnAddContext);
        mPresenter.handleEdInfoContext(mEtInfoContext);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem item_add_info_context = menu.findItem(R.id.action_add_info_context);
        item_add_info_context.setVisible(false);

        return true;
    }
}
