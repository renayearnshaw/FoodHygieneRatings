package ratings.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Authority {
	@JsonProperty("LocalAuthorityId") private Long id;
	@JsonProperty("Name") private String name;
  
    public Authority() {
    }

}
