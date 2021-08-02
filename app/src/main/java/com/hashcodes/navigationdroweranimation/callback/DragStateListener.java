package com.hashcodes.navigationdroweranimation.callback;


public interface DragStateListener {

    void onDragStart();

    void onDragEnd(boolean isMenuOpened);
}
