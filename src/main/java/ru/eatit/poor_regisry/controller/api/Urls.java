package ru.eatit.poor_regisry.controller.api;

public interface Urls {
    String API = "/api/registry";
    String VERSION = "/v1";
    String BASE_URL = API + VERSION;

    String USER_URL = BASE_URL + "/user";
    String SMEV_URL = VERSION + "/smev";
    String POOR_REGISTRY_URL = BASE_URL + "/subsidy";
}
