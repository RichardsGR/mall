package com.gr.common.constant;

public class WareConstant {

    public enum PurchaseStatusEnum{
        CREATE(0,"新建"),
        ASSIGNED(1,"已分配"),
        RECEIVE(2,"已领取"),
        Finish(3,"已完成"),
        HASERROR(4,"有异常");

        private int code;
        private String msg;

        PurchaseStatusEnum(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }
        public int getCode() {
            return this.code;
        }
        public String getMsg() {
            return this.msg;
        }
    }

    public enum PurchaseDetailStatusEnum{
        CREATE(0,"新建"),
        BUYING(2,"正在采购"),
        ASSIGN(1,"已分配"),
        Finish(3,"已完成"),
        HASERROR(4,"采购失败");

        private int code;
        private String msg;

        PurchaseDetailStatusEnum(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }
        public int getCode() {
            return this.code;
        }
        public String getMsg() {
            return this.msg;
        }
    }
}
