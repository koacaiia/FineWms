package fine.koacaiia.finewms;

public class IncargoList {

    String bl;
    String description;
    String date;
    String count;
    String container;
    String incargo;
    String remark;
    String container40;
    String container20;
    String lclcargo;
    String working;
    String location;
    String consignee;

    public IncargoList(){

    }

    public IncargoList(String bl, String description, String date, String count, String container, String incargo, String remark, String container40, String container20, String lclcargo, String working, String location, String consignee) {
        this.bl = bl;
        this.description = description;
        this.date = date;
        this.count = count;
        this.container = container;
        this.incargo = incargo;
        this.remark = remark;
        this.container40 = container40;
        this.container20 = container20;
        this.lclcargo = lclcargo;
        this.working = working;
        this.location = location;
        this.consignee = consignee;
    }

    public String getBl() {
        return bl;
    }

    public void setBl(String bl) {
        this.bl = bl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getContainer() {
        return container;
    }

    public void setContainer(String container) {
        this.container = container;
    }

    public String getIncargo() {
        return incargo;
    }

    public void setIncargo(String incargo) {
        this.incargo = incargo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getContainer40() {
        return container40;
    }

    public void setContainer40(String container40) {
        this.container40 = container40;
    }

    public String getContainer20() {
        return container20;
    }

    public void setContainer20(String container20) {
        this.container20 = container20;
    }

    public String getLclcargo() {
        return lclcargo;
    }

    public void setLclcargo(String lclcargo) {
        this.lclcargo = lclcargo;
    }

    public String getWorking() {
        return working;
    }

    public void setWorking(String working) {
        this.working = working;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }
}
