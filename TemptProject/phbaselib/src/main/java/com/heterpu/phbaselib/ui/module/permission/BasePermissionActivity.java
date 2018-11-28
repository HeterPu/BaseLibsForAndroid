package com.heterpu.phbaselib.ui.module.permission;
import android.Manifest;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.EventLog;
import android.util.Log;
import android.widget.Toast;

import com.heterpu.phbaselib.R;

import java.lang.ref.ReferenceQueue;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;


public class BasePermissionActivity extends AppCompatActivity {


    private PermissionCallBack permissionCallBack;
    private PermissRequestion permissionRequestion;

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
           if (!permissionCallBack.allowed(requstCode)){
               // defalut allow configure
           }
       }else {
           // defalut allow configure
       }
    }


    public void permission_onDenied(int requstCode) {
        // NOTE: Deal with a denied permission, e.g. by showing specific UI
        if ( null !=permissionCallBack){
            if (!permissionCallBack.denied(requstCode)){
                // defalut  configure
                Toast.makeText(this,R.string.permission_camera_denied, Toast.LENGTH_SHORT).show();
            }
        }else {
            // defalut deny configure
            Toast.makeText(this,R.string.permission_camera_denied, Toast.LENGTH_SHORT).show();
        }
    }


    public  void permission_showRationale(final PermissionRequest request, int requestCode) {

        if ( null !=permissionCallBack){
            PermissRequestion requstion = new PermissRequestion() {
                @Override
                public void proceed() {
                    request.proceed();
                }

                @Override
                public void cancel() {
                    request.cancel();
                }
            };

            if (!permissionCallBack.rationale(requstion, requestCode)){
                // defalut  configure
                showRationaleDialog(R.string.permission_camera_rationale, request);
            }
        }else {
            // defalut rationale configure
            showRationaleDialog(R.string.permission_camera_rationale, request);
        }
    }

    public  void permission_onNeverAskAgain(int requstCode) {

        if ( null !=permissionCallBack){
            if (!permissionCallBack.neverask(requstCode)){
                // defalut neverask  configure

                Toast.makeText(this, R.string.permission_camera_never_ask_again, Toast.LENGTH_SHORT).show();
            }
        }else {
            // defalut neverask configure
            Toast.makeText(this, R.string.permission_camera_never_ask_again, Toast.LENGTH_SHORT).show();
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
        boolean rationale(PermissRequestion request, int requestCode);
        boolean neverask(int requestCode);
    }

    public interface PermissRequestion{
        void proceed();
        void cancel();
    }
}
