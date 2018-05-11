package ratings.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Establishment {
	
	@JsonProperty("BusinessName") private String name;
	@JsonProperty("RatingValue") private Rating rating;

    public Establishment() {
    }

}
