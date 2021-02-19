package pixelmoba.server;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;
import pixelmoba.shared.Network;

public class ServerConfiguration {

    private float tickRate;
    private float serverFPS;
    private Network network;

    private static final String PATH = "./server_config.json";

    public static ServerConfiguration loadConfig() {
        Json json = new Json(JsonWriter.OutputType.json);
        json.setIgnoreUnknownFields(true);

        FileHandle file = new FileHandle(PATH);

        if (file.exists())
            try {
                return json.fromJson(ServerConfiguration.class, file);
            } catch (Exception ex) {
                Gdx.app.debug("Server Config", "Invalid server configuration file!");
            }

        ServerConfiguration serverConfig = createConfig();
        json.toJson(serverConfig, file);
        return serverConfig;
    }

    private static ServerConfiguration createConfig() {
        ServerConfiguration config = new ServerConfiguration();
        config.network = new Network(15000, 5455, 5477, "127.0.0.1");
        config.tickRate = 1f / 30f; // 1s / 30 frames = 30fps
        config.serverFPS = 1f / 120f;

        return config;
    }

    public Network getNetwork() {
        return network;
    }

    public float getTickRate() {
        return tickRate;
    }

    public float getServerFPS() {
        return serverFPS;
    }
}
