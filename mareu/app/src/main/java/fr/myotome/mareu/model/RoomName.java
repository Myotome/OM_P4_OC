package fr.myotome.mareu.model;

import fr.myotome.mareu.R;

public enum RoomName {
    SAMUS(R.drawable.circle_samus),
    MARIO(R.drawable.circle_mario),
    LUIGI(R.drawable.circle_luigi),
    PEACH(R.drawable.circle_peach),
    YOSHI(R.drawable.circle_yoshi),
    SONIC(R.drawable.circle_sonic),
    BOWSER(R.drawable.circle_bowser),
    ZELDA(R.drawable.circle_zelda),
    LINK(R.drawable.circle_link),
    WARIO(R.drawable.circle_wario);

    private final int mDrawable;


    /**
     * Associate drawable to enum
     * @param drawable in res/drawable
     */
    RoomName(int drawable) {
        mDrawable=drawable;
    }

    /**
     * @return drawable associate to enum
     */
    public int getDrawable() {
        return mDrawable;
    }
}
