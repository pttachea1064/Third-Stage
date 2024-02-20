package cn.tedu.coolshark.pojo;


public class JsonResult {
    /*狀態碼 status code*/
    private Integer state = 1; //1表示OK 0表示Error
    /*狀態信息 status message*/
    private String message = "OK"; // 表示運行順利
    /*封裝正確的查詢結果*/
    private Object data; //用最大的類型去封裝Object的原因是因為你不曉得你查詢的結果是甚麼類型只好用Object來封裝

    public JsonResult() {
    }

    public JsonResult(String message) { //當修改成功或是新增成功等使用到的方法
        this.message = message; //this.message 通過構造方法的message參數傳入的方法去修改當前的message屬性
    }

    public JsonResult(Integer state, String message) {
        this(message);//this()調用前一個構造方法中的參數 如果參數多的時候就很好用
        this.state = state;
    }

    public JsonResult(Object data) {//查詢時會使用到
        this.data = data;
    }

    //當出現異常時候 可以通過此構造對於異常訊息進行封裝
    public JsonResult(Throwable exception){//Throwable是異常的最高等級的超類
        this(0,exception.getMessage());
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
