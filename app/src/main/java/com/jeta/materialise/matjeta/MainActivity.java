package com.jeta.materialise.matjeta;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.jeta.materialise.JETAapp;
import com.jeta.materialise.model.Message;
import com.jeta.materialise.presenter.MainActivityPresenter;
import com.rey.material.widget.ProgressView;

public class MainActivity extends AppCompatActivity {

    //Views
    private ListView mLvConversations;
    private EditText mEtUserInput;
    private Button mBtnAsk;
    private ProgressView mProgressView;
    private TextView tvLoading;

    //Presenter
    private MainActivityPresenter mPresenter;

    private boolean mIsBusy;

    public MainActivity(){
        super();
        mPresenter = new MainActivityPresenter(this);
        JETAapp.setMainActivity(this);
        mIsBusy = false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLvConversations = (ListView) findViewById(R.id.lv_conversations);
        mEtUserInput = (EditText) findViewById(R.id.et_user_input);
        mBtnAsk = (Button) findViewById(R.id.btn_ask);
        mProgressView = (ProgressView) findViewById(R.id.pv_conversation);
        tvLoading = (TextView) findViewById(R.id.tv_loading);

        mPresenter.handleEdUserInput(mEtUserInput);
        mPresenter.handleBtnAsk(mBtnAsk);
        mPresenter.handleLvConversations(mLvConversations);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d("JETA", "Resuming.");

        int size = JETAapp.getMessageManager().getMessages().size();
        Log.d("JETA", Integer.toString(size));

        for (Message curr : JETAapp.getMessageManager().getMessages()) {
            String curr_msg = curr.getMessage();
            Log.d("JETA", curr_msg);
        }

        Log.d("JETA", "");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem item_add_info_context = menu.findItem(R.id.action_add_info_context);

        if(!item_add_info_context.isVisible())
            item_add_info_context.setVisible(true);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch(id)
        {
            case R.id.action_settings:
                return true;
            case R.id.action_add_info_context:
                if(mIsBusy)
                    return true;

                Intent intent = new Intent(this, AddInfoContextActivity.class);
                startActivity(intent);
            default:
                break;
        }

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setBusy(boolean isBusy){
        mIsBusy = isBusy;
    }

    public boolean isBusy(){
        return mIsBusy;
    }

    public void handleBusy(){
        if(mIsBusy){
            mProgressView.setVisibility(View.VISIBLE);
            tvLoading.setVisibility(View.VISIBLE);
            mLvConversations.setVisibility(View.INVISIBLE);
            mEtUserInput.setVisibility(View.INVISIBLE);
            mBtnAsk.setVisibility(View.INVISIBLE);
        }
        else{
            mProgressView.setVisibility(View.GONE);
            tvLoading.setVisibility(View.GONE);
            mLvConversations.setVisibility(View.VISIBLE);
            mEtUserInput.setVisibility(View.VISIBLE);
            mBtnAsk.setVisibility(View.VISIBLE);
        }
    }

    public MainActivityPresenter getPresenter(){
        return mPresenter;
    }

}
