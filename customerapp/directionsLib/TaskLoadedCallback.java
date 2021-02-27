package com.lec.customerapp.directionsLib;

import com.lec.customerapp.mdel.MapDiration;
import com.lec.customerapp.mdel.MapTime;
/**
 * Created by Vishal on 10/20/2018.
 */

public interface TaskLoadedCallback {
    void onTaskDone(Object... values);

    void onDistance(MapDiration distance);
    void onTIme(MapTime time);
}
