package com.model;

public class Chat {
	private int chat_id;
	private int m_id;
	private int w_id;
	private String managerName;
	private String workerName;
	
	public Chat(int chat_id, int m_id, int w_id, String managerName, String workerName) {
		super();
		this.chat_id = chat_id;
		this.m_id = m_id;
		this.w_id = w_id;
		this.managerName = managerName;
		this.workerName = workerName;
	}

	public int getChat_id() {
		return chat_id;
	}

	public int getM_id() {
		return m_id;
	}

	public int getW_id() {
		return w_id;
	}

	public String getManagerName() {
		return managerName;
	}

	public String getWorkerName() {
		return workerName;
	}
	
	
	
	
	
}
