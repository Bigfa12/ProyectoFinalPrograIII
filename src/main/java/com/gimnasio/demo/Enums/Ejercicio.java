package com.gimnasio.demo.Enums;

public enum Ejercicio {

    // Pecho
    PRESS_BANCA,
    PRESS_INCLINADO,
    PRESS_DECLINADO,
    APERTURAS_CON_MANCUERNAS,
    APERTURAS_EN_PECK_DECK,
    FONDOS_EN_PARALELAS_PARA_PECHO,

    // Espalda
    DOMINADAS,
    JALON_AL_PECHO,
    REMO_EN_MAQUINA,
    REMO_CON_BARRA,
    REMO_CON_MANCUERNA,
    PULLOVER_EN_POLEA,

    // Piernas
    SENTADILLA,
    PRENSA_DE_PIERNA,
    ZANCADAS,
    ELEVACION_TALONES_DE_PIE,
    ELEVACION_TALONES_SENTADO,
    EXTENSION_DE_CUADRICEPS,
    CURL_FEMORAL_ACOSTADO,
    CURL_FEMORAL_SENTADO,
    SENTADILLA_HACK,
    SENTADILLA_FRONTAL,

    // Hombros
    PRESS_MILITAR,
    PRESS_CON_MANCUERNAS,
    ELEVACIONES_LATERALES,
    ELEVACIONES_FRONTALES,
    PÁJAROS,
    REMO_AL_MENTÓN,

    // Brazos
    CURL_CON_BARRA,
    CURL_CON_MANCUERNAS,
    CURL_EN_BANCO_SCOTT,
    MARTILLO,
    EXTENSION_DE_TRICEPS_EN_POLEA,
    FONDOS_DE_TRICEPS,
    PATADA_DE_TRICEPS,
    ROMPECRANEOS,

    // Abdominales / Core
    PLANCHA,
    ABDOMINALES_EN_BANCO,
    CRUNCH_EN_MAQUINA,
    GIROS_RUSOS,
    ELEVACION_DE_PIERNAS,
    RUEDA_ABDOMINAL,

    // Cardio
    CINTA_CORRER,
    BICICLETA_FIJA,
    REMO,
    ESCALADORA,
    ELIPTICA,
    AIR_BIKE,
    SPRINTS_EN_CINTA;

    // Método auxiliar para mostrarlo en forma legible
    public String getNombreFormateado() {
        return this.name()
                .toLowerCase()
                .replace("_", " ")
                .replace("de", "de"); // solo mantiene la palabra sin cambios
    }
}
