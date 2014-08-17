package memento;

import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by enrique on 08/08/14.
 */
public class Memento {

    private View mViewState;
    public boolean undone;

    public Memento(View mViewState){
        this.mViewState = mViewState;
        undone = false;
    }
    public View getState(){
        return mViewState;
    }
    public void switchUndone(){
        if(undone) undone=false;
        else undone = true;
    }
    public boolean isUndone(){   //ya fue deshecho?
        if ( undone == true) return true; //si este objeto ya fue deshecho retorno true
        return false;
    }

}
