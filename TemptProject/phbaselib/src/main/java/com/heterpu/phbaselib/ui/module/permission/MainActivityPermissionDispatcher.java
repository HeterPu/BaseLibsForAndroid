package com.heterpu.phbaselib.ui.module.permission;

import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

import java.lang.ref.WeakReference;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.PermissionUtils;

public final class MainActivityPermissionDispatcher {

    public static final int REQUEST_CAMERA = 0;
    private static final String[] PERMISSION_CAMERA = new String[] {"android.permission.CAMERA"};

    public static final int REQUEST_READWRITECONTACTS = 1;
    private static final String[] PERMISSION_READWRITECONTACTS = new String[] {"android.permission.READ_CONTACTS","android.permission.WRITE_CONTACTS"};

    public static final int REQUEST_WRITEEXTERNALSTORAGE = 2;
    private static final String[] PERMISSION_WRITEEXTERNALSTORAGE = new String[] {"android.permission.WRITE_EXTERNAL_STORAGE"};


    private MainActivityPermissionDispatcher() {
    }


    private static String[] getPermissionFromRequestCode(int requestCode){
        switch (requestCode){
            case REQUEST_CAMERA:
                return PERMISSION_CAMERA;
            case REQUEST_READWRITECONTACTS:
                return PERMISSION_READWRITECONTACTS;
            case REQUEST_WRITEEXTERNALSTORAGE:
                return PERMISSION_WRITEEXTERNALSTORAGE;
            default:
                return new String[]{};
        }
    }



    public static void requestPermissionCheck(@NonNull BasePermissionActivity target,int requestCode) {
        if (PermissionUtils.hasSelfPermissions(target, getPermissionFromRequestCode(requestCode))) {
            target.permission_allowed(requestCode);
        } else {
            if (PermissionUtils.shouldShowRequestPermissionRationale(target, getPermissionFromRequestCode(requestCode))) {
                target.permission_showRationale(new MainActivityShowPermissionRequest(target,requestCode),requestCode);
            } else {
               ActivityCompat.requestPermissions(target, getPermissionFromRequestCode(requestCode), requestCode);
            }
        }
    }


    static void onRequestPermissionsResult(@NonNull BasePermissionActivity target, int requestCode,
                                           int[] grantResults) {
                if (PermissionUtils.verifyPermissions(grantResults)) {
                    target.permission_allowed(requestCode);
                } else {
                    if (!PermissionUtils.shouldShowRequestPermissionRationale(target, getPermissionFromRequestCode(requestCode))) {
                        target.permission_onNeverAskAgain(requestCode);
                    } else {
                        target.permission_onDenied(requestCode);
                    }
                }
    }


        private static final class MainActivityShowPermissionRequest implements PermissionRequest {
            private final WeakReference<BasePermissionActivity> weakTarget;
            private  int requestCode;

            private MainActivityShowPermissionRequest(@NonNull BasePermissionActivity target,int requestCode) {
                this.weakTarget = new WeakReference<BasePermissionActivity>(target);
                this.requestCode = requestCode;
            }

            @Override
            public void proceed() {
                BasePermissionActivity target = weakTarget.get();
                if (target == null) return;
                ActivityCompat.requestPermissions(target, getPermissionFromRequestCode(requestCode), requestCode);
            }

            @Override
            public void cancel() {
                BasePermissionActivity target = weakTarget.get();
                if (target == null) return;
                target.permission_onDenied(requestCode);
            }
        }
}
