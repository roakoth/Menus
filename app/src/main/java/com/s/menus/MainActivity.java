package com.s.menus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.view.ActionMode;
import android.support.v7.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    android.support.v7.view.ActionMode mActionMode;

    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.actionmode_context_menu,menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch(item.getItemId()) {
                case R.id.actionmodemenu1:
                    Toast.makeText(MainActivity.this, "Action Mode 1 clicked", Toast.LENGTH_LONG).show();
                    mode.finish();
                    return true;
                case R.id.actionmodemenu2:
                    Toast.makeText(MainActivity.this,"Action Mode 2 clicked",Toast.LENGTH_LONG).show();
                    mode.finish();
                    return true;
                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
mActionMode = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = (TextView)findViewById(R.id.txtTitle);
        this.registerForContextMenu(tv);

        Button button3 = (Button)findViewById(R.id.btnActionPopupMenu);

        Button button = (Button)findViewById(R.id.btnActionModeMenu);
        button.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(mActionMode != null){
                    return false;
                }
              mActionMode = startSupportActionMode(mActionModeCallback);
                return true;
            }


        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.floating_context_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.contextmenu1:
                Toast.makeText(this,"Contextual menu 1 clicked",Toast.LENGTH_LONG).show();
                return true;
            case R.id.contextmenu2:
                Toast.makeText(this,"Contextual menu 2 clicked",Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onContextItemSelected(item);

        }
        //return super.onContextItemSelected(item);

    }

    public void showPopupMenu(View view) {
        PopupMenu pop = new PopupMenu(this,view);
        pop.inflate(R.menu.popup_menu);
        pop.setOnMenuItemClickListener(this);
        pop.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch(item.getItemId()){
            case R.id.popmenu1:
                Toast.makeText(this,"Popup Menu 1 clicked",Toast.LENGTH_LONG).show();
                return true;
            case R.id.popmenu2:
                Toast.makeText(this,"Popup  menu 2 clicked",Toast.LENGTH_LONG).show();
                return true;
            default:
                return false;

        }
        //return false;
    }
}
