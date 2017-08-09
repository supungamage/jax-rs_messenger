package com.fundit.messanger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fundit.messanger.database.Database;
import com.fundit.messanger.model.Profile;

public class ProfileService {

	private static Map<String, Profile> profiles = Database.getProfiles();

	public ProfileService() {
		profiles.put("Supun", new Profile(1, "Supun", "Supun", "Gamage"));
	}

	public List<Profile> getProfiles() {
		return new ArrayList<Profile>(profiles.values());
	}

	public Profile getProfile(String name) {
		return profiles.get(name);
	}

	public Profile addProfile(Profile profile) {
		profile.setId(profiles.size() + 1);
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}

	public Profile updateProfile(Profile profile) {

		if (profile.getProfileName().isEmpty()) {
			return null;
		}
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}

	public Profile removeProfile(String name) {
		return profiles.remove(name);
	}

}
