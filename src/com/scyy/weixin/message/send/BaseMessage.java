package com.scyy.weixin.message.send;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mengyun on 2016/2/29.
 */
public class BaseMessage {

    /*这里
    * 用list是否合适？
    * */

    private List<String> toUsers = new ArrayList<>();

    private List<String> toParties = new ArrayList<>();

    private List<String> toTags = new ArrayList<>();

    private String MsgType;

    private String agentID;

    private String safe;

    public List<String> getToUsers() {
        return toUsers;
    }

    public void setToUsers(List<String> toUsers) {
        this.toUsers = toUsers;
    }

    public List<String> getToParties() {
        return toParties;
    }

    public void setToParties(List<String> toParties) {
        this.toParties = toParties;
    }

    public List<String> getToTags() {
        return toTags;
    }

    public void setToTags(List<String> toTags) {
        this.toTags = toTags;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public String getAgentID() {
        return agentID;
    }

    public void setAgentID(String agentID) {
        this.agentID = agentID;
    }

    public String getSafe() {
        return safe;
    }

    public void setSafe(String safe) {
        this.safe = safe;
    }
}
