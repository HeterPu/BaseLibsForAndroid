package com.heterpu.phbaselib.ui.module.permission;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;

import android.util.Log;
import android.widget.Toast;

import com.heterpu.phbaselib.R;
import com.heterpu.phbaselib.ui.activity.BaseActivity;


import permissions.dispatcher.PermissionRequest;



public class BasePermissionActivity extends BaseActivity {


    private PermissionCallBack permissionCallBack;

    public BasePermissionActivity(){
    }


    public void checkDevicePermissionWith(int requestCode, PermissionCallBack permissionCallBack){
        this.permissionCallBack = permissionCallBack;
        MainActivityPermissionDispatcher.requestPermissionCheck(BasePermissionActivity.this,requestCode);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        MainActivityPermissionDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }


    public void permission_allowed(int requstCode) {
       if ( null !=permissionCallBack){
           if (permissionCallBack.allowed(requstCode))return;
       }
       Log.i("Default allow configure","" + requstCode);
    }


    public void permission_onDenied(int requstCode) {
        // NOTE: Deal with a denied permission, e.g. by showing specific UI
        if ( null !=permissionCallBack){
            if (permissionCallBack.denied(requstCode))return;
        }
        Log.i("Default allow configure","" + requstCode);
    }


    public  void permission_showRationale(final PermissionRequest request, int requestCode) {

        if ( null !=permissionCallBack){
            PermissionRequestion requstion = new PermissionRequestion() {
                @Override
                public void proceed() {
                    request.proceed();
                }

                @Override
                public void cancel() {
                    request.cancel();
                }
            };
            if (permissionCallBack.rationale(requstion, requestCode))return;
        }
        // defalut rationale configure
        showRationaleDialog(getPermissionRationaleTipFrom(requestCode), request);
    }

    public  void permission_onNeverAskAgain(int requstCode) {

        if ( null !=permissionCallBack){
            if (permissionCallBack.neverask(requstCode))return;
        }
            // defalut neverask configure
        Toast.makeText(this, getPermissionNeverAskFrom(requstCode), Toast.LENGTH_SHORT).show();
    }



    protected int getPermissionRationaleTipFrom(int requestCode){
        switch (requestCode){
            case MainActivityPermissionDispatcher.REQUEST_CAMERA:
                return R.string.permission_camera_rationale;
            case MainActivityPermissionDispatcher.REQUEST_READWRITECONTACTS:
                return R.string.permission_contacts_rationale;
            case MainActivityPermissionDispatcher.REQUEST_WRITEEXTERNALSTORAGE:
                return R.string.permission_write_external_rationale;
                default:
                    return R.string.permission_rationale;

        }
    }

    protected int getPermissionNeverAskFrom(int requestCode){
        switch (requestCode){
            case MainActivityPermissionDispatcher.REQUEST_CAMERA:
                return R.string.permission_camera_never_ask_again;
            case MainActivityPermissionDispatcher.REQUEST_READWRITECONTACTS:
                return R.string.permission_contacts_never_ask_again;
            case MainActivityPermissionDispatcher.REQUEST_WRITEEXTERNALSTORAGE:
                return R.string.permission_write_external_never_ask_again;
            default:
                return R.string.permission_rationale;

        }
    }



    private void showRationaleDialog(@StringRes int messageResId, final PermissionRequest request) {
        new AlertDialog.Builder(this)
                .setPositiveButton(R.string.button_allow, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(@NonNull  DialogInterface dialog, int which) {
                        request.proceed();
                    }
                })
                .setNegativeButton(R.string.button_deny, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(@NonNull DialogInterface dialog, int which) {
                        request.cancel();
                    }
                })
                .setCancelable(false)
                .setMessage(messageResId)
                .show();
    }



    public interface PermissionCallBack{
        boolean allowed(int requestCode);
        boolean denied(int requestCode);
        boolean rationale(PermissionRequestion request, int requestCode);
        boolean neverask(int requestCode);
    }

    public interface PermissionRequestion{
        void proceed();
        void cancel();
    }
}
