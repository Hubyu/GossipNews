package com.moon.gossipnews.utils;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;

/**
 * ***********************************************************
 * author: alex
 * time: 16/7/5 下午2:37
 * name:
 * overview:
 * usage:
 * *************************************************************
 */
public class GsonRequest<T> extends Request<T> {
    private Response.Listener<T> mListener;
    private Gson mGson;
    private Class<T> mClazz;

    public GsonRequest(int method, String url, Response.Listener<T> listener, Response.ErrorListener errorListenerlistener, Class<T> clazz) {
        super(method, url, errorListenerlistener);
        this.mListener = listener;
        mGson = new Gson();
        this.mClazz = clazz;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        String parsed;
        try {
            parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return Response.success(mGson.fromJson(parsed, mClazz), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new VolleyError(e));
        }
    }

    @Override
    protected void deliverResponse(T response) {
        if (mListener != null) {
            mListener.onResponse(response);
        }
    }

    @Override
    protected void onFinish() {
        super.onFinish();
        mListener = null;
    }
}
