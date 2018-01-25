package org.dcode.magang.Api;

/**
 * Created by hp on 25/01/2018.
 */

public class UtilsApi {
    public static final String BASE_URL_API = "http://10.0.2.2/";

    // Mendeklarasikan Interface BaseApiService
    public static BaseApiService getAPIService(){
        return Client.getClient(BASE_URL_API).create(BaseApiService.class);
    }
}
