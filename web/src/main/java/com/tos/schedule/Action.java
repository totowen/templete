package com.tos.schedule;

/**
 * @author qq136
 * @date 2017/10/30.
 */
public enum Action {

    /**
     * 产水
     */
    PRODUCEWATER{
        @Override
        public String getAction() {
            return "产水";
        }
    },

    /**
     * 反洗
     */
    BACKWASHING{
        @Override
        public String getAction() {
            return "反洗";
        }
    };


    public abstract String getAction();

}
