package memento;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by enrique on 08/08/14.
 */
public class Originator  {

    View mViewState; //state

    public void setState(View mViewState){
        this.mViewState = mViewState;
    }
    public View getState(){
        return mViewState;
    }
    public Memento save2Memento(){
        return  new Memento(mViewState);
    }
    public void getStateFromMemento(Memento Memento){
        mViewState = Memento.getState();
    }
}
