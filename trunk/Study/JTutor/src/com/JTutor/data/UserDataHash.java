package com.JTutor.data;

import com.JTutor.data.collection.SortedHashMap;

public class UserDataHash extends SortedHashMap {
	public UserDataHash() {
		super();
	}

	public UserData getUserData(String id) {
		return (UserData) super.get(id);
	}

	public void addUserData(String id) {
		if (!this.containsKey(id)) {
			this.put(id, new UserData(id));
		}
	}

}
