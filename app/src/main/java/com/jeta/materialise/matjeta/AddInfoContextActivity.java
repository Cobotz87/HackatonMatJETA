package com.jeta.materialise.matjeta;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.jeta.materialise.JETAapp;
import com.jeta.materialise.presenter.AddInfoContextPresenter;

/**
 * Created by ArifHBtz on 5/29/2015.
 */
public class AddInfoContextActivity extends AppCompatActivity {

    private Button mBtnSentencingt;
    private Button mBtnTokenize;
    private Button mBtnTaggering;
    private Button mBtnNamed;
    private Button mBtnAddContext;
    private EditText mEtInfoContext;

    private AddInfoContextPresenter mPresenter;

    public AddInfoContextActivity(){
        mPresenter = new AddInfoContextPresenter(this);
        JETAapp.setAddInfoContextActivity(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_info_context);

        mBtnSentencingt = (Button)findViewById(R.id.btn_sentencing);
        mBtnTokenize = (Button) findViewById(R.id.btn_tokenize);
        mBtnTaggering = (Button) findViewById(R.id.btn_tagger);
        mBtnNamed = (Button) findViewById(R.id.btn_named_entity);
        mBtnAddContext = (Button) findViewById(R.id.btn_use_this_context);
        mEtInfoContext = (EditText)findViewById(R.id.et_info_context);

        mPresenter.handleBtnSentencing(mBtnSentencingt);
        mPresenter.handleBtnTokenize(mBtnTokenize);
        mPresenter.handleBtnTagger(mBtnTaggering);
        mPresenter.handleBtnNamedEntity(mBtnNamed);
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

    AddInfoContextPresenter getPresenter(){
        return mPresenter;
    }
}
