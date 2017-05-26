package com.androidutils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by Admin on 21-01-2016.
 */
public class ReplaceFragment {

        public static void replaceFragment (Fragment fragment, FragmentManager fragmentManager, int
            layoutId){
            String backStateName =  fragment.getClass().getName();
            String fragmentTag = backStateName;

            boolean fragmentPopped = fragmentManager.popBackStackImmediate (backStateName, 0);
            if (!fragmentPopped && fragmentManager.findFragmentByTag(fragmentTag) == null){ //fragment not in back stack, create it.
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(layoutId, fragment, fragmentTag);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.addToBackStack(backStateName);
                ft.commit();
            }
        }
}
