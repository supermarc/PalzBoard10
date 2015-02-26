package de.marc.palzboard;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import SlidingTabs.SlidingTabItem;

/**
 * Created by mnaegler on 26.02.2015.
 */
public class TabMain extends SlidingTabItem {

    public Switch schleif;

    private Context context;

    public MediaPlayer mp = null;

    public TabMain(Context context) {
        this.context = context;

        layoutRes = R.layout.tab_1;
        titel = "Main";
        listener = new MyInstantiateListener(context);
    }

    private class MyInstantiateListener implements InstantiateListener {

        private Context context;

        public MyInstantiateListener(Context context) {
            this.context = context;
        }

        @Override
        public void afterInstantiate(View view) {
            Button fuckyou = (Button) view.findViewById(R.id.fuckyou);
            Button hermozu = (Button) view.findViewById(R.id.hermozu);
            Button kacklade = (Button) view.findViewById(R.id.kacklade);
            Button leckmich = (Button) view.findViewById(R.id.leckmich);
            Button motherlode = (Button) view.findViewById(R.id.motherlode);
            Button lodemantel = (Button) view.findViewById(R.id.lodemandel);
            Button schungeil = (Button) view.findViewById(R.id.schungeil);
            Button sehrgut = (Button) view.findViewById(R.id.sehrgut);
            Button wiesichskehrt = (Button) view.findViewById(R.id.wiesichs);
            Button olerotweiss = (Button) view.findViewById(R.id.olerotweiss);
            schleif = (Switch) view.findViewById(R.id.switch1);
            schleif.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(mp == null || !mp.isPlaying()) {
                        return;
                    }

                    if(isChecked) {
                        mp.setLooping(true);
                    } else {
                        if(mp != null && mp.isPlaying()) {
                            mp.release();
                            mp = null;
                        }
                    }
                }
            });

            leckmich.setOnClickListener(new MyListener(context, R.raw.leckmich));
            wiesichskehrt.setOnClickListener(new MyListener(context, R.raw.wiesichs));
            olerotweiss.setOnClickListener(new MyListener(context, R.raw.olerotweiss));
            hermozu.setOnClickListener(new MyListener(context, R.raw.hermozu));
            kacklade.setOnClickListener(new MyListener(context, R.raw.kacklade));
            fuckyou.setOnClickListener(new MyListener(context, R.raw.fuckyou));
            motherlode.setOnClickListener(new MyListener(context, R.raw.motherlode));
            lodemantel.setOnClickListener(new MyListener(context, R.raw.lodemandel));
            schungeil.setOnClickListener(new MyListener(context, R.raw.schungeil));
            sehrgut.setOnClickListener(new MyListener(context, R.raw.sehrgut));
        }
    }

    private class MyListener implements View.OnClickListener {
        private Context context;
        private int sound;
        private String text;

        public MyListener(Context context, int sound) {
            this(context, sound, "");
        }

        public MyListener(Context context, int sound, String text) {
            this.context = context;
            this.sound = sound;
            this.text = text;
        }

        @Override
        public void onClick(View v) {
            if(mp != null && mp.isPlaying()) {
                mp.release();
                mp = null;
            }

            mp = MediaPlayer.create(context.getApplicationContext(), this.sound);

            if(schleif.isChecked()) {
                try {
                    mp.setLooping(true);
                    mp.start();
                    //MainActivity.this.mp.prepareAsync();
                }
                catch(IllegalStateException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    mp.start();
                }
                catch(IllegalStateException e) {
                    e.printStackTrace();
                }
            }

            if(!this.text.equals("")){
                Toast.makeText(context.getApplicationContext(), this.text, Toast.LENGTH_SHORT).show();
            }
        }
    }

}
