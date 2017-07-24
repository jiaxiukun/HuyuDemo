package baway.com.huyudemo.bean;

/**
 * description
 * Created by 贾秀坤 on 2017/5/10.
 */

public class Register {

    /**
     * ret_code : 200
     * ret_msg : 注册成功
     * register : {"id":1,"password":"123456","phone":"18701317750","stats":false,"time":"1494405581696"}
     */

    private int ret_code;
    private String ret_msg;
    private RegisterBean register;

    public int getRet_code() {
        return ret_code;
    }

    public void setRet_code(int ret_code) {
        this.ret_code = ret_code;
    }

    public String getRet_msg() {
        return ret_msg;
    }

    public void setRet_msg(String ret_msg) {
        this.ret_msg = ret_msg;
    }

    public RegisterBean getRegister() {
        return register;
    }

    public void setRegister(RegisterBean register) {
        this.register = register;
    }

    public static class RegisterBean {
        /**
         * id : 1
         * password : 123456
         * phone : 18701317750
         * stats : false
         * time : 1494405581696
         */

        private int id;
        private String password;
        private String phone;
        private boolean stats;
        private String time;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public boolean isStats() {
            return stats;
        }

        public void setStats(boolean stats) {
            this.stats = stats;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
