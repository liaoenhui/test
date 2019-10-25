package com.zysj.dssync.entity;

public class Datasource {
    private String dsId;
    private String dsEname;
    private String dsCname;
    private Integer dsType;
    private String dsIp;
    private String dsPort;
    private String dsUsername;
    private String dsPassword;
    private String dsEndpoint;
    private String dsAccesskey;
    private String dsAccessSecret;
    private String dsVersionId;
    private String dsOperator;
    private String dsUpdatetime;
    private Integer dsSyncState;

    @Override
    public String toString() {
        return "Datasource{" +
                "dsId='" + dsId + '\'' +
                ", dsEname='" + dsEname + '\'' +
                ", dsCname='" + dsCname + '\'' +
                ", dsType=" + dsType +
                ", dsIp='" + dsIp + '\'' +
                ", dsPort='" + dsPort + '\'' +
                ", dsUsername='" + dsUsername + '\'' +
                ", dsPassword='" + dsPassword + '\'' +
                ", dsEndpoint='" + dsEndpoint + '\'' +
                ", dsAccesskey='" + dsAccesskey + '\'' +
                ", dsAccessSecret='" + dsAccessSecret + '\'' +
                ", dsVersionId='" + dsVersionId + '\'' +
                ", dsOperator='" + dsOperator + '\'' +
                ", dsUpdatetime='" + dsUpdatetime + '\'' +
                ", dsSyncState=" + dsSyncState +
                '}';
    }

    public String getDsId() {
        return dsId;
    }

    public void setDsId(String dsId) {
        this.dsId = dsId;
    }

    public String getDsEname() {
        return dsEname;
    }

    public void setDsEname(String dsEname) {
        this.dsEname = dsEname;
    }

    public String getDsCname() {
        return dsCname;
    }

    public void setDsCname(String dsCname) {
        this.dsCname = dsCname;
    }

    public Integer getDsType() {
        return dsType;
    }

    public void setDsType(Integer dsType) {
        this.dsType = dsType;
    }

    public String getDsIp() {
        return dsIp;
    }

    public void setDsIp(String dsIp) {
        this.dsIp = dsIp;
    }

    public String getDsPort() {
        return dsPort;
    }

    public void setDsPort(String dsPort) {
        this.dsPort = dsPort;
    }

    public String getDsUsername() {
        return dsUsername;
    }

    public void setDsUsername(String dsUsername) {
        this.dsUsername = dsUsername;
    }

    public String getDsPassword() {
        return dsPassword;
    }

    public void setDsPassword(String dsPassword) {
        this.dsPassword = dsPassword;
    }

    public String getDsEndpoint() {
        return dsEndpoint;
    }

    public void setDsEndpoint(String dsEndpoint) {
        this.dsEndpoint = dsEndpoint;
    }

    public String getDsAccesskey() {
        return dsAccesskey;
    }

    public void setDsAccesskey(String dsAccesskey) {
        this.dsAccesskey = dsAccesskey;
    }

    public String getDsAccessSecret() {
        return dsAccessSecret;
    }

    public void setDsAccessSecret(String dsAccessSecret) {
        this.dsAccessSecret = dsAccessSecret;
    }

    public String getDsVersionId() {
        return dsVersionId;
    }

    public void setDsVersionId(String dsVersionId) {
        this.dsVersionId = dsVersionId;
    }

    public String getDsOperator() {
        return dsOperator;
    }

    public void setDsOperator(String dsOperator) {
        this.dsOperator = dsOperator;
    }

    public String getDsUpdatetime() {
        return dsUpdatetime;
    }

    public void setDsUpdatetime(String dsUpdatetime) {
        this.dsUpdatetime = dsUpdatetime;
    }

    public Integer getDsSyncState() {
        return dsSyncState;
    }

    public void setDsSyncState(Integer dsSyncState) {
        this.dsSyncState = dsSyncState;
    }
}
