package com.adgvit.allan.retrofittest.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Allan on 27-07-2017.
 */

public class AnswerResponse {
    @SerializedName("result")
    @Expose
    private Result result;
    @SerializedName("info")
    @Expose
    private List<Info> info = null;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public List<Info> getInfo() {
        return info;
    }

    public void setInfo(List<Info> info) {
        this.info = info;
    }

}
