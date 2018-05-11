package ratings.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

@JsonDeserialize(using = Rating.RatingDeserializer.class)
public enum Rating {
	ZERO_STAR("0", "0-star"),
	ONE_STAR("1", "1-star"),
	TWO_STAR("2", "2-star"),
	THREE_STAR("3", "3-star"),
	FOUR_STAR("4", "4-star"),
	FIVE_STAR("5", "5-star"),
	EXEMPT("Exempt", "Exempt"),
	PASS("Pass", "Pass"),
	PASS_EAT_SAFE("Pass and Eat Safe", "Pass and Eat Safe"),
	NEEDS_IMPROVEMENT("Improvement Required", "Needs Improvement"),
	AWAITING_INSPECTION("Awaiting Inspection", "Awaiting Inspection"),
	AWAITING_PUBLICATION("Awaiting Publication", "Awaiting Publication"),
	UNKNOWN("UNKNOWN", "Rating Type Not Recognised");

	private final String inputName;
	private final String outputName;

    Rating(String inputName, String outputName) {
        this.inputName = inputName;
        this.outputName = outputName;
    }

	@Override
    public String toString() {
        return outputName;
    }

	public static class RatingDeserializer extends StdDeserializer<Rating> {

		public RatingDeserializer() {
			super(Rating.class);
		}

		@Override
		public Rating deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
			final JsonNode jsonNode = jsonParser.readValueAsTree();
			String jsonRating = jsonNode.asText();

			for (Rating rating: Rating.values()) {
				if (rating.inputName.trim().replace(" ", "")
						.equalsIgnoreCase(
								jsonRating.trim().replace(" ", ""))) {
					return rating;
				}
			}
			// If the rating string doesn't match a known constant value set it as UNKNOWN
			return Rating.UNKNOWN;
		}
	}

}
