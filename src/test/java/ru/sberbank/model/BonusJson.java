package ru.sberbank.model;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class BonusJson {
    private final String account;
    private final Integer group;
    private final Integer tb;
    private final String [] agreement;

@JsonCreator
    public BonusJson(
            @JsonProperty("account") String account,
            @JsonProperty("group") Integer group,
            @JsonProperty("tb") Integer tb,
            @JsonProperty("agreement") String [] agreement) {
                this.account = account;
                this.group = group;
                this.tb = tb;
                this.agreement = agreement;
}

    public String getAccount() {
        return account;
    }

    public Integer getGroup() {
        return group;
    }

    public Integer getTb() {
        return tb;
    }

    public String[] getAgreement() {
        return agreement;
    }
}
