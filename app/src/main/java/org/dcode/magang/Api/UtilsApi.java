package org.dcode.magang.Api;

/**
 * Created by hp on 25/01/2018.
 */

public class UtilsApi {
    public static final String BASE_URL_API = "http://192.168.11.62/";

    // Mendeklarasikan Interface BaseApiService
    public static BaseApiService getAPIService(){
        return Client.getClient(BASE_URL_API).create(BaseApiService.class);
    }
}
