package pixelmoba;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;
import pixelmoba.shared.Constants;
import pixelmoba.shared.Network;

public class ClientConfiguration {
    private Network network;
    private ArrayMap<Integer, Constants.ACTIONS> keysBindings;
    private ArrayMap<Integer, Constants.ACTIONS> buttonBindings;

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
        config.keysBindings = new ArrayMap<>();
        config.keysBindings.ordered = false;
        config.keysBindings.put(Input.Keys.Q, Constants.ACTIONS.BASIC_1);
        config.keysBindings.put(Input.Keys.W, Constants.ACTIONS.BASIC_2);
        config.keysBindings.put(Input.Keys.E, Constants.ACTIONS.BASIC_3);
        config.keysBindings.put(Input.Keys.R, Constants.ACTIONS.Ultimate);
        config.keysBindings.put(Input.Keys.SPACE, Constants.ACTIONS.FOLLOW_CAMERA);

        config.buttonBindings = new ArrayMap<>();
        config.buttonBindings.ordered = false;
        config.buttonBindings.put(Input.Buttons.RIGHT, Constants.ACTIONS.MOVE);

        return config;
    }

    public Network getNetwork() {
        return network;
    }

    public ArrayMap<Integer, Constants.ACTIONS> getKeysBindings() {
        return keysBindings;
    }

    public ArrayMap<Integer, Constants.ACTIONS> getButtonBindings() {
        return buttonBindings;
    }
}
