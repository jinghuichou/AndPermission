/*
 * Copyright © Zhenjie Yan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.yanzhenjie.permission;

import android.content.Context;
import android.net.Uri;
import android.os.Build;

import com.yanzhenjie.permission.checker.PermissionChecker;
import com.yanzhenjie.permission.checker.StandardChecker;
import com.yanzhenjie.permission.source.ContextSource;
import com.yanzhenjie.permission.source.FragmentSource;
import com.yanzhenjie.permission.source.Source;
import com.yanzhenjie.permission.source.XFragmentSource;

import java.io.File;
import java.util.List;

import androidx.fragment.app.Fragment;

/**
 * Created by Zhenjie Yan on 2016/9/9.
 */
public class AndPermission {

    /**
     * With {@link Fragment}.
     *
     * @param fragment {@link Fragment}.
     *
     * @return {@link Options}.
     */
    public static Options with(Fragment fragment) {
        return new Options(new XFragmentSource(fragment));
    }

    /**
     * With {@link android.app.Fragment}.
     *
     * @param fragment {@link android.app.Fragment}.
     *
     * @return {@link Options}.
     */
    public static Options with(android.app.Fragment fragment) {
        return new Options(new FragmentSource(fragment));
    }

    /**
     * With context.
     *
     * @param context {@link Context}.
     *
     * @return {@link Options}.
     */
    public static Options with(Context context) {
        return new Options(new ContextSource(context));
    }

    /**
     * Some privileges permanently disabled, may need to set up in the execute.
     *
     * @param fragment {@link Fragment}.
     * @param deniedPermissions one or more permissions.
     *
     * @return true, other wise is false.
     */
    public static boolean hasAlwaysDeniedPermission(Fragment fragment, List<String> deniedPermissions) {
        return hasAlwaysDeniedPermission(new XFragmentSource(fragment), deniedPermissions);
    }

    /**
     * Some privileges permanently disabled, may need to set up in the execute.
     *
     * @param fragment {@link android.app.Fragment}.
     * @param deniedPermissions one or more permissions.
     *
     * @return true, other wise is false.
     */
    public static boolean hasAlwaysDeniedPermission(android.app.Fragment fragment, List<String> deniedPermissions) {
        return hasAlwaysDeniedPermission(new FragmentSource(fragment), deniedPermissions);
    }

    /**
     * Some privileges permanently disabled, may need to set up in the execute.
     *
     * @param context {@link Context}.
     * @param deniedPermissions one or more permissions.
     *
     * @return true, other wise is false.
     */
    public static boolean hasAlwaysDeniedPermission(Context context, List<String> deniedPermissions) {
        return hasAlwaysDeniedPermission(new ContextSource(context), deniedPermissions);
    }

    /**
     * Has always been denied permission.
     */
    private static boolean hasAlwaysDeniedPermission(Source source, List<String> deniedPermissions) {
        for (String permission : deniedPermissions) {
            if (!source.isShowRationalePermission(permission)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Some privileges permanently disabled, may need to set up in the execute.
     *
     * @param fragment {@link Fragment}.
     * @param deniedPermissions one or more permissions.
     *
     * @return true, other wise is false.
     */
    public static boolean hasAlwaysDeniedPermission(Fragment fragment, String... deniedPermissions) {
        return hasAlwaysDeniedPermission(new XFragmentSource(fragment), deniedPermissions);
    }

    /**
     * Some privileges permanently disabled, may need to set up in the execute.
     *
     * @param fragment {@link android.app.Fragment}.
     * @param deniedPermissions one or more permissions.
     *
     * @return true, other wise is false.
     */
    public static boolean hasAlwaysDeniedPermission(android.app.Fragment fragment, String... deniedPermissions) {
        return hasAlwaysDeniedPermission(new FragmentSource(fragment), deniedPermissions);
    }

    /**
     * Some privileges permanently disabled, may need to set up in the execute.
     *
     * @param context {@link Context}.
     * @param deniedPermissions one or more permissions.
     *
     * @return true, other wise is false.
     */
    public static boolean hasAlwaysDeniedPermission(Context context, String... deniedPermissions) {
        return hasAlwaysDeniedPermission(new ContextSource(context), deniedPermissions);
    }

    /**
     * Has always been denied permission.
     */
    private static boolean hasAlwaysDeniedPermission(Source source, String... deniedPermissions) {
        for (String permission : deniedPermissions) {
            if (!source.isShowRationalePermission(permission)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Classic permission checker.
     */
    private static final PermissionChecker PERMISSION_CHECKER = new StandardChecker();

    /**
     * Judgment already has the target permission.
     *
     * @param fragment {@link Fragment}.
     * @param permissions one or more permissions.
     *
     * @return true, other wise is false.
     */
    public static boolean hasPermissions(Fragment fragment, String... permissions) {
        return hasPermissions(fragment.getContext(), permissions);
    }

    /**
     * Judgment already has the target permission.
     *
     * @param fragment {@link android.app.Fragment}.
     * @param permissions one or more permissions.
     *
     * @return true, other wise is false.
     */
    public static boolean hasPermissions(android.app.Fragment fragment, String... permissions) {
        return hasPermissions(fragment.getActivity(), permissions);
    }

    /**
     * Judgment already has the target permission.
     *
     * @param context {@link Context}.
     * @param permissions one or more permissions.
     *
     * @return true, other wise is false.
     */
    public static boolean hasPermissions(Context context, String... permissions) {
        return PERMISSION_CHECKER.hasPermission(context, permissions);
    }

    /**
     * Judgment already has the target permission.
     *
     * @param fragment {@link Fragment}.
     * @param permissions one or more permission groups.
     *
     * @return true, other wise is false.
     */
    public static boolean hasPermissions(Fragment fragment, String[]... permissions) {
        return hasPermissions(fragment.getContext(), permissions);
    }

    /**
     * Judgment already has the target permission.
     *
     * @param fragment {@link android.app.Fragment}.
     * @param permissions one or more permission groups.
     *
     * @return true, other wise is false.
     */
    public static boolean hasPermissions(android.app.Fragment fragment, String[]... permissions) {
        return hasPermissions(fragment.getActivity(), permissions);
    }

    /**
     * Judgment already has the target permission.
     *
     * @param context {@link Context}.
     * @param permissions one or more permission groups.
     *
     * @return true, other wise is false.
     */
    public static boolean hasPermissions(Context context, String[]... permissions) {
        for (String[] permission : permissions) {
            boolean hasPermission = PERMISSION_CHECKER.hasPermission(context, permission);
            if (!hasPermission) return false;
        }
        return true;
    }

    /**
     * Get compatible Android 7.0 and lower versions of Uri.
     *
     * @param fragment {@link Fragment}.
     * @param file apk file.
     *
     * @return uri.
     */
    public static Uri getFileUri(Fragment fragment, File file) {
        return getFileUri(fragment.getContext(), file);
    }

    /**
     * Get compatible Android 7.0 and lower versions of Uri.
     *
     * @param fragment {@link android.app.Fragment}.
     * @param file apk file.
     *
     * @return uri.
     */
    public static Uri getFileUri(android.app.Fragment fragment, File file) {
        return getFileUri(fragment.getActivity(), file);
    }

    /**
     * Get compatible Android 7.0 and lower versions of Uri.
     *
     * @param context {@link Context}.
     * @param file apk file.
     *
     * @return uri.
     */
    public static Uri getFileUri(Context context, File file) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return FileProvider.getUriForFile(context, context.getPackageName() + ".file.path.share", file);
        }
        return Uri.fromFile(file);
    }

    private AndPermission() {
    }
}