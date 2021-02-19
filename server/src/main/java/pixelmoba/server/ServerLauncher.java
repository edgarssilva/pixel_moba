package pixelmoba.server;

import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;

public class ServerLauncher {
    public static void main(String[] args) {
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        ServerConfiguration serverConfig = ServerConfiguration.loadConfig();
        config.renderInterval = serverConfig.getServerFPS();
        new HeadlessApplication(new GameServer(serverConfig), config);
    }
}