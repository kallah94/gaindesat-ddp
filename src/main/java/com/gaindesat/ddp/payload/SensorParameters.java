package com.gaindesat.ddp.payload;

public final class SensorParameters {
    public final String HAUTEUR_DE_L_EAU = "Hateur_de_l_eau";
    public final String TEMPERATURE_DE_L_EAU = "Temperature_de_l_eau";
    public final String TEMPERATURE_AMBIANTE = "Temperature_ambiante";
    public final String PRECIPITATION = "Precipitation";
    public final String VITESSE_DU_VENT = "Vitesse_du_vent";

    public String getHAUTEUR_DE_L_EAU() {
        return HAUTEUR_DE_L_EAU;
    }

    public String getTEMPERATURE_DE_L_EAU() {
        return TEMPERATURE_DE_L_EAU;
    }

    public String getTEMPERATURE_AMBIANTE() {
        return TEMPERATURE_AMBIANTE;
    }

    public String getPRECIPITATION() {
        return PRECIPITATION;
    }

    public String getVITESSE_DU_VENT() {
        return VITESSE_DU_VENT;
    }
}
