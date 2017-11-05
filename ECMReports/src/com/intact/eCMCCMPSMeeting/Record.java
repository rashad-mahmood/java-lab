package com.intact.eCMCCMPSMeeting;

public class Record {
	
	private String id;
	private String title;
	private String assignee; 
	private String assignmentGroup;
	private String openTime;
	private String solution;
	private String updateAction;
	
	public Record() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Record(String id, String title, String assignee, String assignmentGroup, String openTime, String solution,
			String updateAction) {
		super();
		this.id = id;
		this.title = title;
		this.assignee = assignee;
		this.assignmentGroup = assignmentGroup;
		this.openTime = openTime;
		this.solution = solution;
		this.updateAction = updateAction;
	}

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getAssignee() {
		return assignee;
	}

	public String getAssignmentGroup() {
		return assignmentGroup;
	}

	public String getOpenTime() {
		return openTime;
	}

	public String getSolution() {
		return solution;
	}

	public String getUpdateAction() {
		return updateAction;
	}
	
	
	
	
	
	
	

}
