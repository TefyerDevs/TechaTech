package net.tefyert.techatech.api.chemical;

public enum ChemicalIdentifiers {
    HYDROGEN("hydrogen","Hydrogen",0xd9f2ff,1),
    HELIUM("helium","Helium",0xbfd7e3,2),
    LITHIUM("lithium","Lithium",0xffdf40,3),
    BERYLLIUM("beryllium","Beryllium",0x404040,4),
    ;

    final Chemical m_chemical;
    ChemicalIdentifiers(String p_id,String p_name, int p_colour_id, int p_atomic_number){
        this.m_chemical = new Chemical(p_id,p_colour_id,p_atomic_number);
    }

    public Chemical get_chemical(){
        return this.m_chemical;
    }
}
