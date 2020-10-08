package pixelmoba.server;

import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import pixelmoba.shared.Network;

public class ServerLauncher {
    public static void main(String[] args) {
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        config.renderInterval = Network.SERVER_FPS;
        new HeadlessApplication(new GameServer(), config);
    }
}