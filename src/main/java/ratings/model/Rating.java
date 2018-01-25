package ratings.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Rating {
	@JsonProperty("1") ONE_STAR("1-star"),
	@JsonProperty("2") TWO_STAR("2-star"),
	@JsonProperty("3") THREE_STAR("3-star"),
	@JsonProperty("4") FOUR_STAR("4-star"),
	@JsonProperty("5") FIVE_STAR("5-star"),
	@JsonProperty("Exempt") EXEMPT("Exempt"),
	@JsonProperty("Pass") PASS("Pass"),
	@JsonProperty("Pass and Eat Safe") PASS_EAT_SAFE("Pass and Eat Safe"),
	@JsonProperty("Improvement Required") NEEDS_IMPROVEMENT("Needs Improvement"),
	@JsonProperty("AwaitingInspection") AWAITINGINSPECTION("Awaiting Inspection"),
	@JsonProperty("Awaiting Inspection") AWAITING_INSPECTION("Awaiting Inspection");

    private String name;

    private Rating(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
