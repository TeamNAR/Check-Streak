package check.streak.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Goal {

    private String id;
    private String name;
    private String startdate;
    private String enddate;
    private String enddate2;
    private String starttime;
    private String endtime;
    private String color;
    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("startdate")
	public String getStartDate() {
		return startdate;
	}

	@JsonProperty("startdate")
	public void setStartDate(String startdate) {
		this.startdate = startdate;
	}

	@JsonProperty("enddate")
	public String getEndDate() {
		return enddate;
	}

	@JsonProperty("enddate")
	public void setEndDate(String enddate) {
		this.enddate = enddate;
	}

	@JsonProperty("enddate2")
	public String getEndDate2() {
		return enddate2;
	}

	@JsonProperty("enddate2")
	public void setEndDate2(String enddate2) {
		this.enddate2 = enddate2;
	}

	@JsonProperty("starttime")
	public String getStartTime() {
		return starttime;
	}

	@JsonProperty("starttime")
	public void setStartTime(String starttime) {
		this.starttime = starttime;
	}

	@JsonProperty("endtime")
	public String getEndTime() {
		return endtime;
	}

	@JsonProperty("endtime")
	public void setEndTime(String endtime) {
		this.endtime = endtime;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
