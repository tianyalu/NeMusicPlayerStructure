package com.sty.music.player.structure.bean;

/**
 * Author: ShiTianyi
 * Time: 2021/9/6 0006 20:29
 * Description:
 */
public class Girl {
    private int icon;
    private String like;
    private String style;

    public Girl() {
    }

    public Girl(int icon, String like, String style) {
        this.icon = icon;
        this.like = like;
        this.style = style;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }
}
