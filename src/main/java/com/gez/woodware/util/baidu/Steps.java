package com.gez.woodware.util.baidu;

public class Steps {
	private String instructions;

	private String path;
 
	private String turn_type;
	private String distance;
	private Object stepOriginLocation;
	private Object stepDestinationLocation;

	
	
	
	public Object getStepOriginLocation() {
		return stepOriginLocation;
	}

	public void setStepOriginLocation(Object stepOriginLocation) {
		this.stepOriginLocation = stepOriginLocation;
	}

	public Object getStepDestinationLocation() {
		return stepDestinationLocation;
	}

	public void setStepDestinationLocation(Object stepDestinationLocation) {
		this.stepDestinationLocation = stepDestinationLocation;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
 
	}

	public String getTurn_type() {
		return turn_type;
	}

	public void setTurn_type(String turn_type) {
		this.turn_type = turn_type;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

 

}
