package com.jeta.materialise.presenter;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.jeta.materialise.adapter.ConversationAdapter;
import com.jeta.materialise.matjeta.AddInfoContextActivity;
import com.jeta.materialise.matjeta.MainActivity;

/**
 * Created by ArifHBtz on 5/29/2015.
 */
public class AddInfoContextPresenter {

    private AddInfoContextActivity mActivity;

    public AddInfoContextPresenter(AddInfoContextActivity activity){
        mActivity = activity;
    }

    public void handleEdInfoContext(EditText edInfoContext){
     //TODO
    }

    public void handleBtnUseContext(Button btnUseContext) {

        btnUseContext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createConfirmationAlertBox();
            }
        });
    }

    private void createConfirmationAlertBox(){
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setTitle("Confirm?");
        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //TODO
                dialog.dismiss();
                mActivity.finish();
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //TODO
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
