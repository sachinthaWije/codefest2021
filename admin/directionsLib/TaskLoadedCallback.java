package com.lec.rider.directionsLib;


import com.lec.rider.model.MapDiration;
import com.lec.rider.model.MapTime;

/**
 * Created by Vishal on 10/20/2018.
 */

public interface TaskLoadedCallback {
    void onTaskDone(Object... values);

    void onDistance(MapDiration distance);
    void onTIme(MapTime time);
}
