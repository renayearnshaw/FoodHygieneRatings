package ratings.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Authority {
	@JsonProperty("LocalAuthorityId") private Long id;
	@JsonProperty("Name") private String name;
  
    public Authority() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
    public String toString() {
        return "Authority{" +
                "id='" + id + '\'' +
                ", name=" + name +
                '}';
    }
}
