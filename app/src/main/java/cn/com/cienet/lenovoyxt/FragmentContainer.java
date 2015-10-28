package cn.com.cienet.lenovoyxt;

import android.support.v4.app.Fragment;
import android.util.SparseArray;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Tanck
 * @Description : TODO
 * @date 10/28/2015
 */
public class FragmentContainer {

    public static List<Fragment> list = new ArrayList<>();


    public static void addFragment(Fragment fragment) {
        if (!list.contains(fragment))
            list.add(fragment);
    }
}
