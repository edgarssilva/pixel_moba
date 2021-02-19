package pixelmoba;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;
import pixelmoba.shared.Network;

public class ClientConfiguration {
    private Network network;

    private static final String PATH = "./config.json";

    public static ClientConfiguration loadConfig() {
        Json json = new Json(JsonWriter.OutputType.json);
        json.setIgnoreUnknownFields(true);

        FileHandle file = new FileHandle(PATH);

        if (file.exists())
            try {
                return json.fromJson(ClientConfiguration.class, file);
            } catch (Exception ex) {
                Gdx.app.debug("Client Config", "Invalid client configuration file!");
            }

        ClientConfiguration clientConfig = createConfig();
        json.toJson(clientConfig, file);
        return clientConfig;
    }

    private static ClientConfiguration createConfig() {
        ClientConfiguration config = new ClientConfiguration();
        config.network = new Network(15000, 5455, 5477, "127.0.0.1");

        return config;
    }

    public Network getNetwork() {
        return network;
    }


}
