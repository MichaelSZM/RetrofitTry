package com.szm.administrator.retrofittry.apis;


import com.szm.administrator.retrofittry.models.User;

import retrofit2.Callback;

/**
 * Created by Administrator on 2016/4/8.
 */
public interface UserApi {

    User getUser(String id, Callback<User> callback);

}
