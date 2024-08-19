package net.tefyert.techatech.api.chemical;

public enum ChemicalIdentifiers {
    HYDROGEN("hydrogen","Hydrogen",0xd9f2ff,1,true),
    HELIUM("helium","Helium",0xbfd7e3,2,true),
    LITHIUM("lithium","Lithium",0xffdf40,3,false),
    BERYLLIUM("beryllium","Beryllium",0x404040,4,false),
    ;

    final Chemical m_chemical;
    ChemicalIdentifiers(String p_id,String p_name, int p_colour_id, int p_atomic_number,boolean ispellet){
        this.m_chemical = new Chemical(p_id,p_colour_id,p_atomic_number,ispellet);
    }

    public Chemical get_chemical(){
        return this.m_chemical;
    }
}
