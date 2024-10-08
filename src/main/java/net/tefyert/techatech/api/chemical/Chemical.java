package net.tefyert.techatech.api.chemical;

public class Chemical {
    String id = "null";
    int colour_id = 0x0;
    int atomic_number = 0;

    boolean is_pellet;

    public Chemical(String p_id, int p_colour_id, int p_atomic_number,boolean is_pellet) {
        this.id = p_id;
        this.colour_id = p_colour_id;
        this.atomic_number = p_atomic_number;
        this.is_pellet = is_pellet;
    }

    public String get_id() {
        return id;
    }
    public int get_colour_id() {
        return colour_id;
    }

    public int get_atomic_number() {
        return atomic_number;
    }

    public boolean is_pellet() {
        return is_pellet;
    }
}
