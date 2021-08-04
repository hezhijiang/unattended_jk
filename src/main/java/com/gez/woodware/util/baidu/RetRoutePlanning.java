package com.gez.woodware.util.baidu;

import java.util.List;

public class RetRoutePlanning {
	private String distance;
	private String duration;
	private List<String> stepsList;

	private List<Object> stepsOri;

	public List<Object> getStepsOri() {
		return stepsOri;
	}

	public void setStepsOri(List<Object> stepsOri) {
		this.stepsOri = stepsOri;
	}

	public String getDistance() {
		if(distance == null) {
			distance="0";
		}
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String getDuration() {
		if(duration == null) {
			duration="0";
		}
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public List<String> getStepsList() {
		return stepsList;
	}

	public void setStepsList(List<String> stepsList) {
		this.stepsList = stepsList;
	}

	@Override
	public String toString() {
		return "RetRoutePlanning [distance=" + distance + ", duration=" + duration + ", stepsList=" + stepsList
				+ ", stepsOri=" + stepsOri + ", getStepsOri()=" + getStepsOri() + ", getDistance()=" + getDistance()
				+ ", getDuration()=" + getDuration() + ", getStepsList()=" + getStepsList() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

}
