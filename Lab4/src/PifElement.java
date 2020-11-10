public class PifElement {
    private String tokenName;
    private Integer hashPosition;
    private Integer listPosition;

    public PifElement(String tokenName, Integer hashPosition, Integer listPosition) {
        this.tokenName = tokenName;
        this.hashPosition = hashPosition;
        this.listPosition = listPosition;
    }

    public String getTokenName() {
        return tokenName;
    }

    public void setTokenName(String tokenName) {
        this.tokenName = tokenName;
    }

    public Integer getHashPosition() {
        return hashPosition;
    }

    public void setHashPosition(Integer hashPosition) {
        this.hashPosition = hashPosition;
    }

    public Integer getListPosition() {
        return listPosition;
    }

    public void setListPosition(Integer listPosition) {
        this.listPosition = listPosition;
    }
}
