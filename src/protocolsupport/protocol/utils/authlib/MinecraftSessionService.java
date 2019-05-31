package protocolsupport.protocol.utils.authlib;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import protocolsupport.api.utils.Profile;
import protocolsupport.api.utils.ProfileProperty;
import protocolsupport.utils.JsonUtils;

public class MinecraftSessionService {

	private static final String hasJoinedUrl = "https://sessionserver.mojang.com/session/minecraft/hasJoined";

	public static void checkHasJoinedServerAndUpdateProfile(GameProfile profile, String hash, String ip) throws AuthenticationUnavailableException, MalformedURLException {
		final URL url = new URL(hasJoinedUrl + "?username=" + profile.getOriginalName() + "&serverId=" + hash + (ip != null ? "&ip=" + ip : ""));
		try {
			JsonObject root = new JsonParser().parse(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8)).getAsJsonObject();
			profile.setOriginalName(JsonUtils.getString(root, "name"));
			profile.setOriginalUUID(UUIDTypeAdapter.fromString(JsonUtils.getString(root, "id")));
			JsonArray properties = JsonUtils.getJsonArray(root, "properties");
			for (JsonElement property : properties) {
				JsonObject propertyobj = property.getAsJsonObject();
				profile.addProperty(new ProfileProperty(
					JsonUtils.getString(propertyobj, "name"),
					JsonUtils.getString(propertyobj, "value"),
					JsonUtils.getString(propertyobj, "signature")
				));
			}
		} catch (IOException | IllegalStateException | JsonParseException e) {
			//System.out.println("[DynamicSlots] " + e);
			String query = "https://api.mojang.com/users/profiles/minecraft/";
			try {
				URL urlt = new URL(query + profile.getOriginalName());
				JsonObject root = new JsonParser().parse(new InputStreamReader(urlt.openStream(), StandardCharsets.UTF_8)).getAsJsonObject();
				//JsonArray properties = JsonUtils.getJsonArray(root, "properties");
				//for (JsonElement property : properties) {
				//	if (property == null) return;
				//}
				throw new AuthenticationUnavailableException();
			} catch (JsonIOException | JsonSyntaxException | IOException | IllegalStateException e1) {
				//System.out.println("Allowed!");
				profile.setOriginalUUID(Profile.generateOfflineModeUUID(profile.getName()));
			}
		}
	}

	public static class AuthenticationUnavailableException extends Exception {
		private static final long serialVersionUID = 1L;
	}

}

