package com.jeta.materialise.matjeta;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.jeta.materialise.presenter.MainActivityPresenter;

public class MainActivity extends AppCompatActivity {

    //Views
    private ListView mLvConversations;
    private EditText mEtUserInput;
    private Button mBtnAsk;

    //Presenter
    private MainActivityPresenter mPresenter;

    public MainActivity(){
        super();
        mPresenter = new MainActivityPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLvConversations = (ListView) findViewById(R.id.lv_conversations);
        mEtUserInput = (EditText) findViewById(R.id.et_user_input);
        mBtnAsk = (Button) findViewById(R.id.btn_ask);

        mPresenter.handleEdUserInput(mEtUserInput);
        mPresenter.handleBtnAsk(mBtnAsk);
        mPresenter.handleLvConversations(mLvConversations);
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

}
