package com.example.vstorage.Service;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class NavigationService {

    public static void clearBackStack(FragmentManager fragmentManager) {
        int backStackEntry = fragmentManager.getBackStackEntryCount();
        if (backStackEntry > 0) {
            for (int i = 0; i < backStackEntry; i++) {
                fragmentManager.popBackStackImmediate();
            }
        }

        if (fragmentManager.getFragments() != null && fragmentManager.getFragments().size() > 0) {
            for (int i = 0; i < fragmentManager.getFragments().size(); i++) {
                Fragment mFragment = fragmentManager.getFragments().get(i);
                if (mFragment != null) {
                    fragmentManager.beginTransaction().remove(mFragment).commit();
                }
            }
        }
    }

    public static void popFragment(FragmentManager fragmentManager, Fragment fragment)
    {
        FragmentTransaction fTrans = fragmentManager.beginTransaction();
        fTrans.remove(fragment);
        fTrans.commit();
    }

    public static void pushFragment(FragmentManager fragmentManager, int containerViewId, Fragment fragment) {
        FragmentTransaction fTrans = fragmentManager.beginTransaction();
        fTrans.replace(containerViewId, fragment);
        fTrans.addToBackStack(null);
        fTrans.commit();
    }
}
